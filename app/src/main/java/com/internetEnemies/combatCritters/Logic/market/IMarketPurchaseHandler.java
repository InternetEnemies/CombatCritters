package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.Logic.transaction.UnverifiedTransactionException;
import com.internetEnemies.combatCritters.objects.VendorTransaction;

/**
 * IMarketPurchaseHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    interface for handling market purchases
 */
public interface IMarketPurchaseHandler {
    /**
     * purchase a vendor offer
     * @param transaction transaction to purchase
     * @return change in reputation
     * @throws UnverifiedTransactionException if the purchase fails
     */
    int purchase(VendorTransaction transaction);
}
