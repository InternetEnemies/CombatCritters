package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBUserModel;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.List;

public class VendorOfferDB extends HSQLDBUserModel implements IVendorOfferDB {
    private final VendorDetails vendorDetails;

    public VendorOfferDB(String dbPath, User user, VendorDetails vendorDetails) {
        super(dbPath, user);
        this.vendorDetails = vendorDetails;
    }

    @Override
    public VendorTransaction getVendorOffer(int offerId) {
        return null;//todo
    }

    @Override
    public List<VendorTransaction> getAllVendorOffers() {
        return List.of();//todo
    }
}
