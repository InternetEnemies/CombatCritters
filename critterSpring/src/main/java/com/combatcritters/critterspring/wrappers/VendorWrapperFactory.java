package com.combatcritters.critterspring.wrappers;

import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.Logic.market.IVendorFactory;
import com.internetEnemies.combatCritters.data.market.IVendorOfferDBFactory;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

public class VendorWrapperFactory implements IVendorFactory {
    private final User user;
    private final IVendorOfferDBFactory vendorOfferDBFactory;

    public VendorWrapperFactory(User user, IVendorOfferDBFactory vendorOfferDBFactory) {
        this.user = user;
        this.vendorOfferDBFactory = vendorOfferDBFactory;
    }


    @Override
    public IVendor getVendor(VendorDetails vendorDetails) {
        return new VendorWrapper(vendorDetails, vendorOfferDBFactory.create(vendorDetails, user));
    }
}
