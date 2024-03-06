/**
 * IMarketHandler.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      03-March-2024
 *
 * @PURPOSE:     Handle the the MarketDB offers and pass the offer to TransactionHandler
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;

public interface IMarketHandler {

    /**
     * getter for a single offer
     * @param index the index of the offer
     * @return a single MarketTransaction with given index
     */
    MarketTransaction getOffer(int index);

    /**
     * getter for every offers
     * @return a list of available offers from MarketDB
     */
    List<MarketTransaction> getOffers();

    /**
     *  pass the offer to transactionHandler
     * @param index the index of offer user want to take
     */
    void visitOffer(int index);
}
