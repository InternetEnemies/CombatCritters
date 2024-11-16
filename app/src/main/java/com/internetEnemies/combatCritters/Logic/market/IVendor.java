package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.DiscountTransaction;
import com.internetEnemies.combatCritters.objects.VendorTransaction;
import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.util.List;

/**
 * IVendor.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    represents a single vendor
 */
public interface IVendor {

    /**
     * get the details of this vendor
     */
    VendorDetails getDetails();

    /**
     * get a transaction from its id
     * @param id id of the transaction to get
     * @return the transaction with the given id if it exists
     * @throws com.internetEnemies.combatCritters.Logic.exceptions.NXTransactionException if the transaction with the given id does not exist
     */
    VendorTransaction getOffer(int id);

    /**
     * get all the transactions for this vendor
     * @return list of transaction from this vendor
     */
    List<VendorTransaction> getOffers();

    /**
     * get the specials offers for this vendor
     * @return list of special offers
     */
    List<VendorTransaction> getSpecialOffers();

    /**
     * get the discount offers for this vendor
     * @return list of DiscountTransactions
     */
    List<DiscountTransaction> getDiscountOffers();
}
