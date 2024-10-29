package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * IVendorFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    interface for creating vendors from details
 */
public interface IVendorFactory {
    /**
     * get a vendor from its details
     * @param vendorDetails details of the vendor
     * @return vendor related to the given details 
     */
    IVendor getVendor(VendorDetails vendorDetails);
}
