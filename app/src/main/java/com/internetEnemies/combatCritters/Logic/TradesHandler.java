/**
 * TradesHandler.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      29-February-2024
 *
 * @PURPOSE:     Show all available offers in the trade registry
 *               Pass on the selected offer to the transaction Handler
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.TradeTransaction;

import java.util.List;

public class TradesHandler implements ITradesHandler{
    private final IRegistry<TradeTransaction> tradeRegistry;

    public TradesHandler(IRegistry<TradeTransaction> tradeRegistry){
        this.tradeRegistry = tradeRegistry;
    }

    //TODO: null constructor
    // should adapt implementation from TradeDB (not sure the name), like in PackCatalog

    @Override
    public TradeTransaction getOffer(int index) {
        return tradeRegistry.getSingle(index);
    }

    @Override
    public List<TradeTransaction> getOffers() {
        return tradeRegistry.getAll();
    }

    //TODO
    // waiting for Transaction Handler interface
    @Override
    public void visitOffer(int index) {

    }
}
