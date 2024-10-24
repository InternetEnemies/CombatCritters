package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SQLExecutor.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/24/24
 * 
 * @PURPOSE:    execute sql queries and updates
 */
public class SQLExecutor {
    /**
     * execute the operation without returning anything
     * @param operation operation to execute
     * @param connectionFactory connection provider
     * @param statementFactory statement provider
     * @param onError string thrown if an error occurs
     */
    public static void execute(
            ISQLOperation operation,
            IConnectionFactory connectionFactory, 
            IStatementFactory statementFactory, 
            String onError){
        try(Connection connection = connectionFactory.getConnection(); 
            PreparedStatement preparedStatement = statementFactory.getStatement(connection)){
            operation.execute(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(onError,e);
        }
    }

    /**
     * execute a query and return the result
     * @param operation operation to perform
     * @param connectionFactory connection provider
     * @param statementFactory statement provider
     * @param onError string that is thrown when an error occurs
     * @return query result
     * @param <T> type of the query result
     */
    public static <T> T execute(
            ISQLQueryOperation<T> operation,
            IConnectionFactory connectionFactory,
            IStatementFactory statementFactory,
            String onError){
        T result;
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = statementFactory.getStatement(connection)){
            result = operation.execute(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(onError,e);
        }
        return result;
    }
}
