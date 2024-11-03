package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.VendorRep;

/**
 * IVendorRepManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    manager a vendors rep
 */
public interface IVendorRepManager {
    /**
     * add reputation to a vendor
     * @param amount amount of reputation to add (set negative to remove)
     */
    void addRep(int amount);

    /**
     * get the reputation of the vendor managed by this manager
     * @return VendorRep object for the vendor
     */
    VendorRep getVendorRep();
    
}
