package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * IVendorRepDBFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    provide VendorRepDB's
 */
public interface IVendorRepDBFactory {
    IVendorRepDB create(User user, VendorDetails vendorDetails);
}
