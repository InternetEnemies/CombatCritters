package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.objects.VendorTransaction;

import java.util.List;

public interface IVendorOfferDB {
    /**
     * get a vendor offer from its id
     * @param offerId id of the offer to get
     * @return offer with the given id
     */
    VendorTransaction getVendorOffer(int offerId);

    /**
     * get all vendor offers
     */
    List<VendorTransaction> getAllVendorOffers();

    /**
     * get vendors special offers
     */
    List<VendorTransaction> getSpecialOffers();
}
