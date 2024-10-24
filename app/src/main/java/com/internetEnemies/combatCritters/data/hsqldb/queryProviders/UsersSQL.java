package com.internetEnemies.combatCritters.data.hsqldb.queryProviders;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * UsersSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/23/24
 * 
 * @PURPOSE:    provide sql for users
 */
public class UsersSQL {
    public static PreparedStatement getUsers(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM users");
    }
    
    public static IStatementFactory banUser(int id) {
        return (connection -> {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET banned = true WHERE id = ?");
            statement.setInt(1, id);
            return statement;
        });
    }
}
