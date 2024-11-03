package com.internetEnemies.combatCritters.data.hsqldb.queryProviders;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.PreparedStatement;

/**
 * CardSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/2/24
 * 
 * @PURPOSE:    provide sql for card related routes
 */
public class CardSQL {
    public static IStatementFactory getCard(int id) {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cards WHERE id = ?");
            statement.setInt(1, id);
            return statement;
        };
    }
}
