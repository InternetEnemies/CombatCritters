package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers;

import java.sql.ResultSet;
import java.sql.SQLException; /**
 * interface for get result callback
 * @param <T> type of result
 */
public interface IResultExtractor<T> {
    /**
     * gets single result from a resultset
     * @param rs result set to process
     * @return object from the result set
     */
    T getResult(ResultSet rs) throws SQLException;
}
