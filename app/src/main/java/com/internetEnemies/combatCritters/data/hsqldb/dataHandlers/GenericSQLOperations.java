package com.internetEnemies.combatCritters.data.hsqldb.dataHandlers;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.ISQLOperation;

import java.sql.PreparedStatement;

/**
 * Provide some basic sql operations
 */
public class GenericSQLOperations {
    /**
     * sql operation for executing an update with no additional processing
     */
    public static ISQLOperation update() {
        return PreparedStatement::executeUpdate;
    }
}
