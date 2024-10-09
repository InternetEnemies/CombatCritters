package com.internetEnemies.combatCritters.Logic.users;

import com.internetEnemies.combatCritters.objects.User;

/**
 * IFriendsManagerFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    provide interface to get new friends managers
 */
public interface IFriendsManagerFactory {
    IFriendsManager getFriendsManager(User user);
}
