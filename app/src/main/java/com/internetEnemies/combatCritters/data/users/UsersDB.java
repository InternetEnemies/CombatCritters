package com.internetEnemies.combatCritters.data.users;

import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.ResultListExtractor;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.UserHelper;
import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBModel;
import com.internetEnemies.combatCritters.data.hsqldb.queryProviders.ProfilesSQL;
import com.internetEnemies.combatCritters.data.hsqldb.queryProviders.UsersSQL;
import com.internetEnemies.combatCritters.objects.User;

import java.sql.*;
import java.util.List;

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
            
            //create the profile for the new user
            PreparedStatement addProfile = ProfilesSQL.createProfile(connection, user);
            addProfile.executeUpdate();
            
        } catch (SQLException e){
            throw new RuntimeException("Database error while creating user",e);
        }
        
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user;
        try (Connection connection = this.connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            } else {
                user = null;
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while getting the user",e);
        }

        return user;
    }

    @Override
    public User getUserById(int id) {
        User user;
        try (Connection connection = this.connection()) {
            final PreparedStatement statement= connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        id,
                        resultSet.getString("username"), 
                        resultSet.getString("password")
                );
            } else {
                user = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("an error occurred while getting the user",e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try(Connection connection = this.connection(); PreparedStatement statement = UsersSQL.getUsers(connection)) {
            ResultSet resultSet = statement.executeQuery();
            users = ResultListExtractor.getResults(resultSet, UserHelper::fromResultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while getting all users",e);
        }
        return users;
    }
}
