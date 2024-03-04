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
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;

public class MarketHandler implements IMarketHandler{
    private final IRegistry<Transaction> marketDB;

    public MarketHandler(IRegistry<Transaction> marketDB){
        this.marketDB = marketDB;
    }

    //TODO: null constructor
    //  should adapt implementation from MarketDB, see PackCatalog

    @Override
    public Transaction getOffer(int index) {
        return marketDB.getSingle(index);
    }

    @Override
    public List<Transaction> getOffers() {
        return marketDB.getAll();
    }

    //TODO
    //  waiting for transaction Handler interface
    @Override
    public void takeOffer(int index) {

    }
}
