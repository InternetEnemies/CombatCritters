package com.internetEnemies.combatCritters.data.hsqldb.queryProviders;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.PreparedStatement;

/**
 * PackSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/2/24
 * 
 * @PURPOSE:    provide sql for packs 
 */
public class PackSQL {
    public static IStatementFactory getPack(int id) {
        return connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM packs WHERE id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        };
    }
}
