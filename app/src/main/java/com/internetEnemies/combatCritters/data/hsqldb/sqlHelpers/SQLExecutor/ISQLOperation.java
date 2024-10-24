package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * interface for sql operations that can be executed
 */
public interface ISQLOperation {
    void execute(PreparedStatement statement) throws SQLException;
}
