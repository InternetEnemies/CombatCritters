package com.internetEnemies.combatCritters.data.users;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBModel;
import com.internetEnemies.combatCritters.objects.User;

import java.sql.*;

/**
 * UsersDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/26/24
 * 
 * @PURPOSE:    sql database implementation for UsersDB
 */
public class UsersDB extends HSQLDBModel implements IUsersDB{
    public UsersDB(String dbPath) {
        super(dbPath);
    }


    @Override
    public User createUser(String username, String password) {
        User user;
        try(Connection connection = this.connection()){
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                int generatedId = generatedKeys.getInt("id");
                user = new User(generatedId, username, password);
            } else {
                throw new RuntimeException("Failed to add user");
            }
            
        } catch (SQLException e){
            throw new RuntimeException("Database error while creating user",e);
        }
        
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }
}
