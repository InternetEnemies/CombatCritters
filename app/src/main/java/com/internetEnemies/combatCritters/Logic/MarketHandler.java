/**
 * IMarketHandler.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      03-March-2024
 *
 * @PURPOSE:     implementation for IMarketHandler
 *               Provide UI for MarketPlace UI
 *               Fetch offers from MarketDB
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MarketHandler implements IMarketHandler{
    private final IRegistry<MarketTransaction> marketDB;
    //private final TransactionHandler transactionHandler;

    public MarketHandler(IRegistry<MarketTransaction> marketDB){
        this.marketDB = marketDB;
        //transactionHandler = new TransactionHandler();
    }

    //TODO: null constructor
    //  should adapt implementation from MarketDB, see PackCatalog

    @Override
    public MarketTransaction getOffer(int index) {
        return marketDB.getSingle(index);
    }

    @Override
    public List<MarketTransaction> getOffers() {
        return marketDB.getAll();
    }

    @Override
    public List<MarketTransaction> getCardOffers() {
        return null;
    }

    @Override
    public List<MarketTransaction> getBundleOffers() {
        return null;
    }

    @Override
    public List<MarketTransaction> getPackOffers() {
        return null;
    }

    //TODO
    //  waiting for transaction Handler interface
    @Override
    public void performTransaction(MarketTransaction offer) {
        //MarketTransaction temp = marketDB.getSingle(index);
        //transactionHandler.verifyTransaction(temp);
    }
}
