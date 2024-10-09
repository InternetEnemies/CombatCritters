package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.objects.UserProfile;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ProfilesHelper.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    convert result sets into Profiles
 */
public class ProfilesHelper {

    /**
     * get a user profile from a result set
     * @param rs result set to user
     * @return UserProfile from rs
     */
    public static UserProfile fromResultSet(ResultSet rs) throws SQLException {
        return new UserProfile(rs.getInt("deckId"));
    }
    
}
