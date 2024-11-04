package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

/**
 * IMarketPurchaseHandlerFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    interface for providing market purchase handlers
 */
public interface IMarketPurchaseHandlerFactory {
    IMarketPurchaseHandler create(User user, VendorDetails vendorDetails);
}
