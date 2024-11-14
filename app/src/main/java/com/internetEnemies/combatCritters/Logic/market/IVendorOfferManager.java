package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;

/**
 * IVendorOfferManager.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    manage offers for all vendors
 */
public interface IVendorOfferManager {
    /**
     * create a new vendor offer
     * @param vendorOfferCreator creation object for the offer
     * @param vendorid id of the vendor to create for
     * @return the created vendor transaction
     */
    VendorTransaction createVendorOffer(VendorOfferCreator vendorOfferCreator, int vendorid);
}
