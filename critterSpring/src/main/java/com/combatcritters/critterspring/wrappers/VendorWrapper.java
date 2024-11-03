package com.combatcritters.critterspring.wrappers;

import com.internetEnemies.combatCritters.Logic.exceptions.NXTransactionException;
import com.internetEnemies.combatCritters.Logic.market.Vendor;
import com.internetEnemies.combatCritters.data.market.IVendorOfferDB;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VendorWrapper extends Vendor {

    public VendorWrapper(VendorDetails vendorDetails, IVendorOfferDB vendorOfferDB) {
        super(vendorDetails, vendorOfferDB);
    }
    
    @Override
    public VendorTransaction getOffer(int id) {
        try {
            return super.getOffer(id);
        } catch(NXTransactionException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Offer not found");
        }
    }
}
