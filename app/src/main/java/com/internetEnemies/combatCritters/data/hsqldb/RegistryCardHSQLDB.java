package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.CardBuilder;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * RegistryCardHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    sql implementation of the card registry
 */
public class RegistryCardHSQLDB implements IRegistry<Card> {
    private final String dbPath;

    public RegistryCardHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Card fromResultSet(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final String image = rs.getString("image");
        final int playCost = rs.getInt("playCost");
        final int rarity = rs.getInt("rarity");
        final String type = rs.getString("type");

        Card card = null;

        List<Integer> abilities = new ArrayList<>();
        Card.Rarity rare = Card.Rarity.values()[rarity];

        switch(type){
            case "critter":
                final int damage = rs.getInt("damage");
                final int health = rs.getInt("health");
                final Integer ability = rs.getInt("abilities");
                abilities.add(ability);
                card = new CritterCard(id, name, image, playCost, rare, damage, health, abilities);
                break;
            case "item":
                final int effectId = rs.getInt("effectId");
                card = new ItemCard(id, name, image, playCost, rare, effectId);
                break;
        }
        return card;
    }

    @Override
    public Card getSingle(int id) {
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cards WHERE id = ?");
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return fromResultSet(resultSet);
            }
            else {
                return null;
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
    }

    @Override
    public List<Card> getAll() {
        List<Card> cards = new ArrayList<>();
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cards");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cards.add(fromResultSet(resultSet));
            }
        } catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return cards;
    }

    @Override
    public List<Card> getListOf(List<Integer> ids) {
        List<Card> cards = new ArrayList<>();
        try (final Connection connection = connection()) {
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Cards WHERE id IN (");
            for (int i = 0; i < ids.size(); i++) {
                queryBuilder.append("?");
                if (i < ids.size() - 1) {
                    queryBuilder.append(",");
                }
            }
            queryBuilder.append(")");
            final PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            for (int i = 0; i < ids.size(); i++) {
                statement.setInt(i + 1, i); // Idk
            }
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cards.add(fromResultSet(resultSet));
            }
        } catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return cards;
    }

    /**
     * util function for testing, id value of card is ignored
     * @param card Card object to create a card from
     * @return Card (with real id) that was created
     */
    public Card addCard(Card card) {
        Card newCard;
        try(final Connection conn = connection()){
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Cards (name, image, playCost, rarity, type, damage, health, effectId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, card.getName());
            stmt.setString(2, card.getImage());
            stmt.setInt(3, card.getPlayCost());
            stmt.setInt(4, card.getRarity().ordinal());
            stmt.setString(5, card instanceof CritterCard ? "critter" : "item");
            if (card instanceof CritterCard) {
                CritterCard critterCard = (CritterCard) card;
                stmt.setInt(6, critterCard.getDamage());
                stmt.setInt(7, critterCard.getHealth());
                stmt.setNull(8, Types.INTEGER);
            } else if (card instanceof ItemCard) {
                ItemCard itemCard = (ItemCard) card;
                stmt.setNull(6, Types.INTEGER);
                stmt.setNull(7, Types.INTEGER);
                stmt.setInt(8, itemCard.getEffectId());
            }
            stmt.executeUpdate();
            ResultSet created = stmt.getGeneratedKeys();
            if (created.next()){
                int key = created.getInt(1);
                CardBuilder builder= new CardBuilder();
                card.clone(builder);
                builder.setId(key);
                newCard = builder.build();

            } else {
                throw new RuntimeException("Failed to create new Card");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating a card", e);
        } catch (CardBuilder.InvalidCardException e) {
            throw new RuntimeException("Invalid Card, cannot create",e);
        }
        return newCard;
    }

    /**
     * util function for dumping all cards to the console
     */
    public void dumpCards(){
        for (Card card :this.getAll()) {
            System.out.println(card);
        }
    }
}
