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
     * getter for Card offers
     * @return a list of available offers containing card
     */
    List<MarketTransaction> getCardOffers();

    /**
     * getter for Bundle offers
     * @return a list of available offers containing more than 1 items
     */
    List<MarketTransaction> getBundleOffers();

    /**
     * getter for Pack offers
     * @return a list of available offers containing pack
     */
    List<MarketTransaction> getPackOffers();

    /**
     *  pass the offer to transactionHandler
     * @param offer the offer user want to take
     */
    void performTransaction(MarketTransaction offer);
}
