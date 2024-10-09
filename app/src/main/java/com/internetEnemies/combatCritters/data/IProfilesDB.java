package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.UserProfile;

/**
 * IProfilesDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    user profile database interface
 */
public interface IProfilesDB {
    /**
     * update the users profile
     * @param userProfile profile to update to
     */
    void updateProfile(UserProfile userProfile);

    /**
     * get the users profile
     */
    UserProfile getProfile();
}
