package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.data.market.IVendorDB;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.List;

/**
 * VendorRegistry.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/17/24
 * 
 * @PURPOSE:    registry for vendor details
 */
public class VendorRegistry implements IVendorRegistry {
    private final IVendorDB vendorDB;

    public VendorRegistry(IVendorDB vendorDB) {
        this.vendorDB = vendorDB;
    }

    @Override
    public List<VendorDetails> getVendors() {
        return vendorDB.getAllVendors();
    }

}
