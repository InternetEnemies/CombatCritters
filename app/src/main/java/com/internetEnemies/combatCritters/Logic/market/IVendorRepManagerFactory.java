package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * IVendorRepManagerFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    provide vendorRepManagers
 */
public interface IVendorRepManagerFactory {
    IVendorRepManager create(User user, VendorDetails vendorDetails);
}
