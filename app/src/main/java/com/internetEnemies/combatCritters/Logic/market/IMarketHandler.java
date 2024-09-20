/**
 * IMarketHandler.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      03-March-2024
 *
 * @PURPOSE:     Handle the the MarketDB offers and pass the offer to TransactionHandler
 */

package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.List;
/**
 * IMarketHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-21
 *
 * @PURPOSE:    Interface for MarketHandler, which handles all transaction related to the marketplace.
 */

public interface IMarketHandler {

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
    boolean performTransaction(MarketTransaction offer);

    /**
     * Refreshes the Marketplace's discounts using the MarketCycle class.
     */
    void refreshDiscounts();


}
