package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IFriendsDB;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.UserHelper;
import com.internetEnemies.combatCritters.data.hsqldb.queryProviders.FriendsSQL;
import com.internetEnemies.combatCritters.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * FriendsHSQLDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    handles friends for a user
 */
public class FriendsHSQLDB extends HSQLDBUserModel implements IFriendsDB {
    public FriendsHSQLDB(String dbPath, User user) {
        super(dbPath, user);
    }

    @Override
    public List<User> getFriends() {
        List<User> friends = new ArrayList<>();
        try(Connection connection = this.connection(); PreparedStatement statement = FriendsSQL.getFriends(connection, this.getUser())) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                friends.add(UserHelper.fromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting a users friends",e);
        }
        return friends;
    }

    @Override
    public void addFriend(User user) {
        try(Connection connection = this.connection(); PreparedStatement statement = FriendsSQL.addFriend(connection,this.getUser(), user)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while adding user",e);
        }
    }

    @Override
    public List<User> getPendingFriends() {
        List<User> friends = new ArrayList<>();
        try (Connection connection = this.connection(); PreparedStatement statement = FriendsSQL.getPending(connection, this.getUser())) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                friends.add(UserHelper.fromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error getting pending friend requests",e);
        }
        return friends;
    }
}
