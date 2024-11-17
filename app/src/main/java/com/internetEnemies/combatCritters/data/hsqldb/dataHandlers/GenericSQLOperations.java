package com.internetEnemies.combatCritters.data.hsqldb.dataHandlers;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.IResultExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.ISQLOperation;
import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.ISQLQueryOperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    /**
     * sql operation for updating and getting generated keys
     * @param extractor key extractor
     * @return object from extractor
     */
    public static <T> ISQLQueryOperation<T> updateWithKeys(IResultExtractor<T> extractor) {
        return statement -> {
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            return extractor.getResult(resultSet);
        };
    }

    /**
     * sql operation for executing queries and returning the result
     * @param extractor method for converting the sql result into the desired object
     * @return object from the query
     * @param <T> type of object requested
     */
    public static <T> ISQLQueryOperation<T> query(IResultExtractor<T> extractor){
        return (statement -> {
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            return extractor.getResult(resultSet);
        });
    }
}
