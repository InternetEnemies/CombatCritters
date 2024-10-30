package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.List;

/**
 * IVendorDB.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    handle vendors 
 */
public interface IVendorDB {
    /**
     * get a vendors details from its id
     * @param id id of the vendor to get
     */
    VendorDetails getVendor(int id);

    /**
     * get all vendors in the game
     * @return list of all vendors
     */
    List<VendorDetails> getAllVendors();
}
