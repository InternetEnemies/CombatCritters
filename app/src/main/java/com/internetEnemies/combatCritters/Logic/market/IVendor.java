package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * IVendor.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    represents a single vendor
 */
public interface IVendor {

    /**
     * get the details of this vendor
     */
    VendorDetails getDetails();
}
