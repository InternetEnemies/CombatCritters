package com.combatcritters.critterspring.payloads.market;

import com.internetEnemies.combatCritters.Logic.market.IVendor;
import com.internetEnemies.combatCritters.objects.VendorRep;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.time.Instant;
import java.util.Date;

/**
 * VendorPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/30/24
 * 
 * @PURPOSE:    represents a Vendor for REST
 */
public record VendorPayload(int id, String image, String name, String refresh_time, VendorReputationPayload reputation) {
    public static VendorPayload from(IVendor vendor, VendorRep vendorRep, Date refresh) {
        VendorDetails details = vendor.getDetails();
        return new VendorPayload(details.id(), details.image(), details.name(), 
                refresh.toString(),
                VendorReputationPayload.from(vendorRep)
        );
    }
}
