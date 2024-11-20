package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.List;

/**
 * IVendorRegistry.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/17/24
 * 
 * @PURPOSE:    interface for getting vendor details
 */
public interface IVendorRegistry {
    /**
     * @return list of all vendor details in the registry
     */
    List<VendorDetails> getVendors();
}
