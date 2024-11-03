package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * IVendorOfferDBFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/1/24
 * 
 * @PURPOSE:    interface for getting instances of vendorOfferDbs
 */
public interface IVendorOfferDBFactory {
    /**
     * create an offer db instance for a users vendor
     * @param vendorDetails details of the vendor 
     * @param user user of the vendor
     * @return offer db
     */
    IVendorOfferDB create(VendorDetails vendorDetails, User user);
}
