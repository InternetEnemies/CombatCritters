package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * factory for Prepared Statements
 */
public interface IStatementFactory {
    PreparedStatement getStatement(Connection connection) throws SQLException;
}
