package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IProfilesDB;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.ProfilesHelper;
import com.internetEnemies.combatCritters.data.hsqldb.queryProviders.ProfilesSQL;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfilesHSQLDB extends HSQLDBUserModel implements IProfilesDB {
    public ProfilesHSQLDB(String dbPath, User user) {
        super(dbPath, user);
    }

    @Override
    public void updateProfile(UserProfile userProfile) {
        try (Connection connection = this.connection(); 
             PreparedStatement statement = ProfilesSQL.updateProfile(connection, this.getUser(), userProfile)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("sql error while updating users profile",e);
        }
    }

    @Override
    public UserProfile getProfile() {
        UserProfile userProfile;
        try(Connection connection = this.connection(); 
            PreparedStatement statement = ProfilesSQL.getProfile(connection, this.getUser())) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userProfile = ProfilesHelper.fromResultSet(resultSet);
            } else {
                throw new RuntimeException("profile not found. Does the user exist?"); // 
            }
        } catch (SQLException e) {
            throw new RuntimeException("sql error occurred while getting users profile",e);
        }
        return userProfile;
    }
}
