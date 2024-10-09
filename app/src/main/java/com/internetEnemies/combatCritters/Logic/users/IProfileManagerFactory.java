package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.objects.User;

/**
 * IProfileManagerFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    provide factory interface for profile managers
 */
public interface IProfileManagerFactory {
    ProfileManager getProfileManager(User user);
}
