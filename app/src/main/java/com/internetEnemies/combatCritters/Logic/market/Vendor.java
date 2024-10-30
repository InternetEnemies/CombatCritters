package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * Vendor.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    represents a vendor
 */
public class Vendor implements IVendor {
    private final VendorDetails vendorDetails;

    public Vendor(VendorDetails vendorDetails) {
        if (vendorDetails == null) throw new NullPointerException("Cannot instantiate a null vendor");
        this.vendorDetails = vendorDetails;
    }

    @Override
    public VendorDetails getDetails() {
        return vendorDetails;
    }
}
