package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.data.market.IVendorOfferDBFactory;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * VendorFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    provide a simple vendor factory
 */
public class VendorFactory implements IVendorFactory {
    private final User user;
    private final IVendorOfferDBFactory vendorOfferDBFactory;
    private final IVendorRepManagerFactory vendorRepManagerFactory;

    public VendorFactory(User user, IVendorOfferDBFactory vendorOfferDBFactory, IVendorRepManagerFactory vendorRepManagerFactory) {
        this.user = user;
        this.vendorOfferDBFactory = vendorOfferDBFactory;
        this.vendorRepManagerFactory = vendorRepManagerFactory;
    }
    
    @Override
    public IVendor getVendor(VendorDetails vendorDetails) {
        return new Vendor(vendorDetails, vendorOfferDBFactory.create(vendorDetails, user, vendorRepManagerFactory));
    }
}
