/**
 * IMarketDB.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     interface for MarketDB
 *               manage a list of market offers
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.List;

public interface IMarketDB {

    /**
     *  add a Bundle offer into MarketDB
     * @param offer a Bundle MarketTransaction to add
     */
    void addBundleOffer(MarketTransaction offer);

    /**
     * add a Card offer into MarketDB
     * @param offer a Card MarketTransaction to add
     */
    void addCardOffer(MarketTransaction offer);

    /**
     * add a Pack offer into MarketDB
     * @param offer a Pack MarketTransaction to add
     */
    void addPackOffer(MarketTransaction offer);

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
}
