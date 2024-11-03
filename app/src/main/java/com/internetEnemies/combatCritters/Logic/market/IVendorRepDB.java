package com.internetEnemies.combatCritters.Logic.market;

/**
 * IVendorRepDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    manage vendor reputation in the database
 */
public interface IVendorRepDB {
    /**
     * add reputation to a vendor 
     * @param amount amount to add to the rep
     */
    void addRep(int amount);

    /**
     * get the current rep in the db
     * @return amount of reputation for this vendorDB
     */
    int getRep();
}
