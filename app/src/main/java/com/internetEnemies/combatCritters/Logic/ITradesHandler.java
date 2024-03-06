/**
 * ITradesHandler.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      29-February-2024
 *
 * @PURPOSE:     Handle the available trade offers and pass
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.TradeTransaction;

import java.util.List;

public interface ITradesHandler {

    /**
     * getter for a specific offer
     * @param index the index of the offer wanted
     * @return the transaction in the list
     */
    TradeTransaction getOffer(int index);

    /**
     * getter for the list of offers
     * @return A list of fetched offers
     */
    List<TradeTransaction> getOffers();

    /**
     * pass the chosen offer to transaction Handler
     * @param offer The offer user want to accept
     */
    boolean performTransaction(TradeTransaction offer);
}
