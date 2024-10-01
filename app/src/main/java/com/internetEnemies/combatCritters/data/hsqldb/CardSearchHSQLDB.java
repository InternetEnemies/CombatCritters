package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CardHelper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


/**
 * CardSearchHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    sql implementation of card search
 */
public class CardSearchHSQLDB extends HSQLDBModel implements ICardSearch {

    public CardSearchHSQLDB(final String dbPath) {
        super(dbPath);
    }

    @Override
    public List<ItemStack<Card>> get(List<CardOrder> orders, CardFilter filter) {
        assert orders != null;
        assert filter != null;

        List<ItemStack<Card>> cardStacks = new ArrayList<>();
        try (Connection connection = this.connection()){
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Cards");
            // Apply filters
            queryBuilder.append(getFilterSQL(filter));
            // Apply orders
            if (!orders.isEmpty()) {
                queryBuilder.append(" ORDER BY");
                for(CardOrder order : orders) {
                    queryBuilder.append(" ")
                            .append(getOrderString(order))
                            .append(",");
                }
                queryBuilder.deleteCharAt(queryBuilder.length()-1); // delete last ,
            }

            System.out.println(queryBuilder);

            final PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Card card = CardHelper.cardFromResultSet(resultSet);
                if(card == null) continue;
                int amount = resultSet.getInt("CardInventory.amount");//HSQLDB replaces null ints with 0 (kinda dumb but whatever)
                ItemStack<Card> cardStack = new ItemStack<>(card, amount);
                cardStacks.add(cardStack);
            }
        } catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return cardStacks;
    }

    private String getFilterSQL(CardFilter filter) {
        StringBuilder where = new StringBuilder();
        // * Owned
        if(filter.isOwned()){// ! NOTE this if statement must be first
            where.append(" INNER JOIN CardInventory ON Cards.id = CardInventory.cardId");
        } else {
            where.append(" LEFT JOIN CardInventory ON Cards.id = CardInventory.cardId");
        }


        // * Rarity
        List<Card.Rarity> rarities = filter.getRarities();
        if(!rarities.isEmpty()) { // skip if no rarity specified
            where.append(" WHERE cards.rarity");

            if(!filter.isRarityWhitelist()) where.append(" NOT");
            where.append(" IN (");
            for (Card.Rarity rarity:rarities) {
                where.append(rarity.ordinal())// this should be sqli safe
                        .append(",");
            }
            where.deleteCharAt(where.length()-1); // delete last ,
            where.append(")");

        }

        // * cost
        Integer cost = filter.getCost();
        if(cost != null) {
            where.append(" AND cards.playCost");
            where.append(filter.isLessThan()? " < ": ">=");
            where.append(filter.getCost());
        }

        return where.toString();
    }

    private String getOrderString(CardOrder order) {
        String orderStr = "Cards.";
        switch(order) {
            case ID:
                orderStr += "id";
                break;
            case NAME:
                orderStr += "name";
                break;
            case PLAY_COST:
                orderStr += "playCost";
                break;
            case RARITY:
                orderStr += "rarity";
                break;
            default:
                throw new RuntimeException("Invalid Order (how??? whatever you did it was wrong)");
        }
        return orderStr;
    }


}
