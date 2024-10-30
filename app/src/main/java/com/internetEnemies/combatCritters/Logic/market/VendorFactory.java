package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * VendorFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    provide a simple vendor factory
 */
public class VendorFactory implements IVendorFactory {
    @Override
    public IVendor getVendor(VendorDetails vendorDetails) {
        return new Vendor(vendorDetails);
    }
}
