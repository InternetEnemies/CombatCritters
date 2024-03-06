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
     * get the Market Transaction with given id
     * @param id id of Market Transaction to be found
     * @return Market Transaction with id
     */
    MarketTransaction getSingle(int id);

    /**
     * get list of all Market Transaction stored in this marketDB
     * @return list of Market Transaction
     */
    List getAll();

    /**
     * get list of Market Transaction given a list of ids
     * @param ids list of ids of Market Transaction needed
     * @return list of Market Transaction
     */
    List getListOf(List<Integer> ids);

    /**
     *  add an offer into MarketDB
     * @param offer a MarketTransaction to add
     */
    void add(MarketTransaction offer);

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
