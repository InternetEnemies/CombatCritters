package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.Logic.exceptions.NXTransactionException;
import com.internetEnemies.combatCritters.data.market.IVendorOfferDB;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.List;

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
    private final IVendorOfferDB vendorOfferDB;

    public Vendor(VendorDetails vendorDetails, IVendorOfferDB vendorOfferDB) {
        this.vendorOfferDB = vendorOfferDB;
        if (vendorDetails == null) throw new NullPointerException("Cannot instantiate a null vendor");
        this.vendorDetails = vendorDetails;
    }

    @Override
    public VendorDetails getDetails() {
        return vendorDetails;
    }

    @Override
    public VendorTransaction getOffer(int id) {
        VendorTransaction offer = vendorOfferDB.getVendorOffer(id);
        if (offer == null) {
            throw new NXTransactionException("Cannot find vendor offer");
        }
        return offer;
    }

    @Override
    public List<VendorTransaction> getOffers() {
        return vendorOfferDB.getAllVendorOffers();
    }
}
