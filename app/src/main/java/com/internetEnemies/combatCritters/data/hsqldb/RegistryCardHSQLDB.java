package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistryCardHSQLDB implements IRegistry {
    private final String dbPath;

    public RegistryCardHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Card fromResultSet(final ResultSet rs) throws SQLException {
        final Integer id = rs.getInt("id");
        final String name = rs.getString("name");
        final String image = rs.getString("image");
        final Integer playCost = rs.getInt("playCost");
        final Integer rarity = rs.getInt("rarity");
        final String type = rs.getString("type");

        Card card = null;

        // Dummy list
        List<String> abilities = new ArrayList<>();

        switch(type){
            case "critter":
                final Integer damage = rs.getInt("damage");
                final Integer health = rs.getInt("health");
                //final Integer/List/whatever abilities = rs.getSomethingHere("abilities");
                card = new CritterCard(id, name, image, playCost, rarity/*Card.Rarity.rarity*/, damage, health /*ability*/);
                break;
            case "item":
                final Integer effectId = rs.getInt("effectId");
                card = new ItemCard(id, name, image, playCost, rarity/*Card.Rarity.rarity*/, effectId);
                break;
        }
        return card;
    }

    @Override
    public Object getSingle(int id) {
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
    public List getAll() {
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
    public List getListOf(List ids) {
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
                statement.setInt(i + 1, ids.get(i)); // Idk
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
}
