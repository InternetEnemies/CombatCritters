/**
 * IMarketDB.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     interface for MarketDB
 *               manage a list of market offers
 */

package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.List;
import java.util.Map;

public interface IMarketDB {

    /**
     * return only card offers
     * @return a list of market transaction that only containing card
     */
    List<MarketTransaction> getCardOffers();

    /**
     * return only pack offers
     * @return a list of market transaction that only containing pack
     */
    List<MarketTransaction> getPackOffers();

    /**
     * return transaction that containing more than 1 item
     * @return a list of market transaction that containing bundle
     */
    List<MarketTransaction> getBundleOffers();

    /**
     * Adjusts the discount of the given transactions
     * @param discounts a map of all the ids of transactions that need their discount changed.
     */
    void adjustDiscount(Map<Integer, Double> discounts);

    /**
     * return all the market transactions stored in data
     * @return a list of all market transactions
     */
    List<MarketTransaction> getAllOffers();
}
