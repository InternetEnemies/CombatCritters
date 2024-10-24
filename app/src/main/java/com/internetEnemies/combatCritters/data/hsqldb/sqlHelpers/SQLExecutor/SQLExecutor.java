package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLExecutor {
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
}
