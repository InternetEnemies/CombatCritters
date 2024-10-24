package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.ISQLOperation;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.ISQLQueryOperation;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.SQLExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * HSQLDBModel.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    abstract class that is common for all HSQLDB database interface implementations,
 *              provides connections for subclasses
 */
public abstract class HSQLDBModel {
    private final String path;
    public HSQLDBModel(String dbPath) {
        this.path= dbPath;
    }

    /**
     * get a new connection object
     * @return Connection object for the db
     */
    protected Connection connection() throws SQLException {
        return DriverManager.getConnection(path);
    }

    /**
     * execute an sql operation 
     * @param operation operation to execute
     * @param factory factory for the statement in the operation
     * @param onError message to throw with error
     */
    protected void execute(ISQLOperation operation, IStatementFactory factory, String onError) {
        SQLExecutor.execute(operation, this::connection, factory, onError);
    }
    
    protected <T> T execute(ISQLQueryOperation<T> operation, IStatementFactory factory, String onError) {
        return SQLExecutor.execute(operation, this::connection, factory, onError);
    }
}
