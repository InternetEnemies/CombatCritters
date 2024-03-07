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

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IMarketDB;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.MarketDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MarketHandler implements IMarketHandler{
    private final IMarketDB marketDB;
    private TransactionHandler transactionHandler;

    public MarketHandler(IMarketDB marketDB){
        this.marketDB = marketDB;
        transactionHandler = new TransactionHandler(Database.getInstance().getCardInventory(),Database.getInstance().getPackInventory(), Database.getInstance().getCurrencyInventory());
    }

    public MarketHandler(){
        this(Database.getInstance().getMarketDB());
    }

    @Override
    public List<MarketTransaction> getCardOffers() {
        return marketDB.getCardOffers();
    }

    @Override
    public List<MarketTransaction> getBundleOffers() {
        return marketDB.getBundleOffers();
    }

    @Override
    public List<MarketTransaction> getPackOffers() {
        return marketDB.getPackOffers();
    }

    @Override
    public boolean performTransaction(MarketTransaction offer) {
        assert offer != null;
        return transactionHandler.performTransaction(offer);
    }
}
