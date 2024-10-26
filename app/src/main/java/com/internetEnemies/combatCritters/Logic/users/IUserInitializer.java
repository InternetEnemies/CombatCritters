package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.objects.User;

/**
 * IUserInitializer.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/26/24
 * 
 * @PURPOSE:    interface for initializing users. Sets up things such as: starting currency, starting deck, etc.
 */
public interface IUserInitializer {
    /**
     * initializes data for a user
     * @param user user to initialize
     */
    void initialize(User user);
}
