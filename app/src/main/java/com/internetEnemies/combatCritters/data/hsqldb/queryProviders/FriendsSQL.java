package com.internetEnemies.combatCritters.data.hsqldb.queryProviders;

import com.internetEnemies.combatCritters.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * FriendsSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    provide sql queries for friends table
 */
public class FriendsSQL {
    /**
     * get sql to add a new friend
     * @param user user adding friend
     * @param friend friend tto be added
     */
    public static PreparedStatement addFriend(Connection connection, User user, User friend) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FRIENDS (userTx, userRx) VALUES (?,?)");
        preparedStatement.setInt(1,user.getId());
        preparedStatement.setInt(2,friend.getId());
        return preparedStatement;
    }

    /**
     * get sql for getting a users friends
     * @param user user to get friends of
     */
    public static PreparedStatement getFriends(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FRIENDS LEFT JOIN USERS ON USERS.id = FRIENDS.userRX WHERE userTx=? AND userRx IN (SELECT userTx AS userRx FROM FRIENDS WHERE FRIENDS.userRx=?)");
        preparedStatement.setInt(1,user.getId());
        preparedStatement.setInt(2,user.getId());
        return preparedStatement;
    }

    /**
     * get sql for getting a users pending friend requests
     * @param user user to get pending friends of
     */
    public static PreparedStatement getPending(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FRIENDS LEFT JOIN USERS ON USERS.id = FRIENDS.userTx WHERE userRx=? AND userTx NOT IN (SELECT userRx as userTx FROM FRIENDS WHERE FRIENDS.userTx=?)");
        preparedStatement.setInt(1,user.getId());
        preparedStatement.setInt(2,user.getId());
        return preparedStatement;
    }
}
