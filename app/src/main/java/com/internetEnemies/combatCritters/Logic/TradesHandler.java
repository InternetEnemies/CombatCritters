/**
 * TradesHandler.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      29-February-2024
 *
 * @PURPOSE:     Implementation for ITradesHandler
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;

public class TradesHandler implements ITradesHandler{

    public TradesHandler(){

    }

    /**
     * @return A list of fetched offers fetched from TradeRegistry
     */
    @Override
    public List<Transaction> getOffers() {
        return null;
    }

    /**
     * pass the chosen offer to transaction Handler
     *
     * @param offer The offer user want to accept
     */
    @Override
    public void chooseOffer(Transaction offer) {

    }
}
