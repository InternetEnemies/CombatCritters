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

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.TradeTransaction;

import java.util.List;

public class TradesHandler implements ITradesHandler{
    private final IRegistry<TradeTransaction> tradeRegistry;
    private TransactionHandler transactionHandler;

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
        this(Database.getInstance().getTradeRegistry(),new TransactionHandler(Database.getInstance().getCardInventory(), Database.getInstance().getPackInventory(), Database.getInstance().getCurrencyInventory()));
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
