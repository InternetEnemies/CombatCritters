package com.internetEnemies.combatCritters.data.hsqldb.queryProviders;

import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ProfilesSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    provide sql for interacting with the profiles table
 */
public class ProfilesSQL {

    /**
     * get statement for creating a profile
     * @param user user to create the profile for
     */
    public static PreparedStatement createProfile(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO PROFILES (userid) VALUES (?)");
        statement.setInt(1, user.getId());
        return statement;
    }

    /**
     * get statement for getting a profile
     * @param user user to get the profile for
     */
    public static PreparedStatement getProfile(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PROFILES WHERE userid = ?");
        statement.setInt(1, user.getId());
        return statement;
    }

    /**
     * get statment for updating a profile
     * @param user user of profile being updated
     * @param userProfile new user profile data
     */
    public static PreparedStatement updateProfile(Connection connection, User user, UserProfile userProfile) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE PROFILES SET deckId = ? WHERE userId = ?");
        statement.setInt(1, userProfile.deckId());
        statement.setInt(2, user.getId());
        return statement;
    }
}
