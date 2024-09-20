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

import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.OffersDatabase;
import com.internetEnemies.combatCritters.objects.TradeTransaction;

import java.util.List;

public class TradesHandler implements ITradesHandler{
    private final IRegistry<TradeTransaction> tradeRegistry;
    private final TransactionHandler transactionHandler;

    /**
     * if we already have a list of TradeTransaction
     * @param tradeRegistry a given tradeRegistry
     * @param transactionHandler pass in transaction Handler
     */
    public TradesHandler(IRegistry<TradeTransaction> tradeRegistry, TransactionHandler transactionHandler){
        this.tradeRegistry = tradeRegistry;
        this.transactionHandler = transactionHandler;
    }

    /**
     * null constructor, for take a new list from Database
     */
    public TradesHandler(){
        this(
                OffersDatabase.getInstance().getTradesDB(),
                new TransactionHandler()
        );
    }

    @Override
    public TradeTransaction getOffer(int index) {
        return tradeRegistry.getSingle(index);
    }

    @Override
    public List<TradeTransaction> getOffers() {
        return tradeRegistry.getAll();
    }

    @Override
    public boolean performTransaction(TradeTransaction offer) {
        assert offer != null;
        return transactionHandler.performTransaction(offer);
    }
}
