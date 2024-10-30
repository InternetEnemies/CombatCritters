package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.User;

/**
 * IVendorManagerFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/30/24
 * 
 * @PURPOSE:    interface for providing vendor managers
 */
public interface IVendorManagerFactory {
    /**
     * create a vendor manager for a user
     * @param user user to create manager for
     * @return vendor manager for the user
     */
    IVendorManager create(User user);
}
