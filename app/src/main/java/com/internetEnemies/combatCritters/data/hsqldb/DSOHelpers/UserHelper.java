package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.objects.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserHelper.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    make users from result sets
 */
public class UserHelper {
    public static User fromResultSet(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("banned"));
    }
}
