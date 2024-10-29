package com.internetEnemies.combatCritters.Logic.market;


import java.util.List;

/**
 * IVendorManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    interface for managing all vendors in the game
 */
public interface IVendorManager {
    /**
     * get all the vendors
     */
    List<IVendor> getVendors();

    /**
     * get a single vendor from its id
     * @param id id of the vendor
     */
    IVendor getVendor(int id);
}
