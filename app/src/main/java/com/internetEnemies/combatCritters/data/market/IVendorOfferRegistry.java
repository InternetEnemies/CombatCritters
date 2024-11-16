package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorOfferCreator;

/**
 * IVendorOfferRegistry.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    interface vendor offer management
 */
public interface IVendorOfferRegistry {
    /**
     * get a vendor offer from its id
     */
    VendorTransaction getVendorOffer(int vendorid, int offerid);

    /**
     * create a new vendor offer
     * @param vendorid id of the vendor to create for
     * @param vendorOfferCreator details of the offer to create
     * @return newly created vendor offer
     */
    VendorTransaction createVendorOffer(int vendorid, VendorOfferCreator vendorOfferCreator);

    /**
     * create a new special offer
     * @param vendorid id of the vendor to create for
     * @param vendorOfferCreator details of the offer to create
     * @return newly created special offer
     */
    VendorTransaction createSpecialOffer(int vendorid, VendorOfferCreator vendorOfferCreator);
}
