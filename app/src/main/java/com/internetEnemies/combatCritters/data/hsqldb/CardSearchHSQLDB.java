package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class CardSearchHSQLDB implements ICardSearch {

    private final String dbPath;

    public CardSearchHSQLDB(final String dbPath) {
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

        List<Integer> abilities = new ArrayList<>();
        Card.Rarity rare = Card.Rarity.values()[rarity];

        switch(type) {
            case "critter":
                final Integer damage = rs.getInt("damage");
                final Integer health = rs.getInt("health");
                final Integer ability = rs.getInt("abilities");
                abilities.add(ability);
                card = new CritterCard(id, name, image, playCost, rare, damage, health, abilities);
                break;
            case "item":
                final Integer effectId = rs.getInt("effectId");
                card = new ItemCard(id, name, image, playCost, rare, effectId);
                break;
        }
        return card;
    }

    // TODO
    // The issue here is that i dont really know how this works
    // So i kind of dont know how to properly implement it
    @Override
    public List<ItemStack<Card>> get(List<CardOrder> orders, CardFilter filter) {
        List<ItemStack<Card>> cardStacks = new ArrayList<>();
        try (final Connection connection = connection()) {
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Cards");
            // Apply filters
            if (filter != null) {
                queryBuilder.append(" WHERE ");
                queryBuilder.append(filter.toSQLString()); // GPT told me to do this but this is not a function we have,
            }                                              // Prob need to find a better way to do this
            // Apply orders
            if (orders != null && !orders.isEmpty()) {
                queryBuilder.append(" ORDER BY ");
                for (int i = 0; i < orders.size(); i++) {
                    queryBuilder.append(orders.get(i).toSQLString());
                    if (i < orders.size() - 1) {
                        queryBuilder.append(", ");
                    }
                }
            }
            final PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Card card = fromResultSet(resultSet);
                ItemStack<Card> cardStack = new ItemStack<>(card, 1); // Assuming each card appears only once
                cardStacks.add(cardStack);
            }
        } catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return cardStacks;
    }
}
