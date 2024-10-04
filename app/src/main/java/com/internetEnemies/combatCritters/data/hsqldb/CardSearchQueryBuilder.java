package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ICardFilterBuilder;
import com.internetEnemies.combatCritters.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CardSearchQueryBuilder.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/3/24
 * 
 * @PURPOSE:    provide card search sql query
 */
public class CardSearchQueryBuilder implements ICardFilterBuilder {
    private final String order;
    private final Connection connection;
    boolean innerJoin;
    List<String> filters;
    private User user;

    public CardSearchQueryBuilder(String order, Connection connection) {
        this.order = order;
        this.connection = connection;
        this.innerJoin = false;
        this.filters = new ArrayList<>();
    }
    
    @Override
    public ICardFilterBuilder setRarity(List<Card.Rarity> rarities, boolean whitelist) {
        StringBuilder where = new StringBuilder();
        if(!rarities.isEmpty()) { // skip if no rarity specified
            where.append("              cards.rarity");

            if(!whitelist) where.append(" NOT");
            where.append(" IN (");
            for (Card.Rarity rarity:rarities) {
                where.append(rarity.ordinal())// this should be sqli safe
                        .append(",");
            }
            where.deleteCharAt(where.length()-1); // delete last ,
            where.append(")");

        }
        if(!where.isEmpty())filters.add(where.toString());
        return this;
    }

    @Override
    public ICardFilterBuilder setUser(User user, boolean owned) {
        this.user = user;
        if(owned){
            innerJoin = true;
        }
        if(user != null) {
            filters.add("CardInventory.userId = ?");
        }
        
        return this;
    }

    @Override
    public ICardFilterBuilder setCost(int cost, boolean lessThan) {
        String where = " cards.playCost" +
                (lessThan ? " < " : ">=") +
                cost;
        filters.add(where);
        return this;
    }
    
    public PreparedStatement build() throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM Cards");
        query.append(innerJoin ? " INNER" : " LEFT");
        query.append(" JOIN CardInventory ON Cards.id = CardInventory.cardId");

        if(!filters.isEmpty()) {
            query.append(" WHERE");
        }
        boolean first = true;
        for (String filter:filters) {
            if(!first) query.append(" AND");
            query.append(filter);
            if(first) first = false;
        }
        query.append(order);
        System.out.println(query);
        
        //!! this should be safe
        PreparedStatement statement = connection.prepareStatement(query.toString());
        if(user!=null) {
            statement.setInt(1, user.getId());
        }
        return statement;
    }
}
