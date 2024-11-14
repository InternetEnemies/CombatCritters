package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.data.market.IVendorOfferRegistry;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;

/**
 * VendorOfferManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    manage vendor offers for management interface
 */
public class VendorOfferManager implements IVendorOfferManager {
    private final IVendorOfferRegistry vendorOfferRegistry;

    public VendorOfferManager(IVendorOfferRegistry vendorOfferRegistry) {
        this.vendorOfferRegistry = vendorOfferRegistry;
    }

    @Override
    public VendorTransaction createVendorOffer(VendorOfferCreator vendorOfferCreator, int vendorid) {
        return vendorOfferRegistry.createVendorOffer(vendorid, vendorOfferCreator);
    }
}
