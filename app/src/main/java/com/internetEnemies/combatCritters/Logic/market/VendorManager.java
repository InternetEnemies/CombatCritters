package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.data.market.IVendorDB;

import java.util.List;

/**
 * VendorManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    manage vendors from a vendorDB
 */
public class VendorManager implements IVendorManager {
    private final IVendorDB vendorDB;
    private final IVendorFactory vendorFactory;

    public VendorManager(IVendorDB vendorDB, IVendorFactory vendorFactory) {
        this.vendorDB = vendorDB;
        this.vendorFactory = vendorFactory;
    }
    
    
    @Override
    public List<IVendor> getVendors() {
        return this.vendorDB.getAllVendors().stream().map(vendorFactory::getVendor).toList();
    }

    @Override
    public IVendor getVendor(int id) {
        return vendorFactory.getVendor(vendorDB.getVendor(id));
    }
}
