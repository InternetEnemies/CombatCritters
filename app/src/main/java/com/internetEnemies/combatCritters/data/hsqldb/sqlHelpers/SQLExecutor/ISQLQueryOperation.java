package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * provide interface for SQL query operations
 * @param <T> return type of the operation
 */
public interface ISQLQueryOperation<T> {
    T execute(PreparedStatement statement) throws SQLException;
}
