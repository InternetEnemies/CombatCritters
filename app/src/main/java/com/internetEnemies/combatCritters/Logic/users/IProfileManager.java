package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.objects.UserProfile;


/**
 * IProfileManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    interface for managing a profile
 */
public interface IProfileManager {
    /**
     * get the profile related to this manager
     * @return profile object managed by the manager
     */
    UserProfile getProfile();

    /**
     * update the profile
     * @param profile new profile to update to 
     */
    void updateProfile(UserProfile profile);
}
