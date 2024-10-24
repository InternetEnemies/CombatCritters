package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * interface for providing connections
 */
public interface IConnectionFactory {
    Connection getConnection() throws SQLException;
}
