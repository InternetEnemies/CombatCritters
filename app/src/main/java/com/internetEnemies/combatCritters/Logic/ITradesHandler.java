/**
 * ITradesHandler.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      29-February-2024
 *
 * @PURPOSE:     Handle the available trade offers and pass
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;

public interface ITradesHandler {

    /**
     *
     * @return A list of offers fetched from
     */
    List<Transaction> getOffers();

    /**
     * pass the chosen offer to transaction Handler
     * @param offer The offer user want to accept
     */
    void chooseOffer(Transaction offer);
}
