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
import com.internetEnemies.combatCritters.data.MarketDB;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.List;

public class MarketHandler implements IMarketHandler{
    @Override
    public MarketTransaction getOffer(int index) {
        return null;
    }

    @Override
    public List<MarketTransaction> getOffers() {
        return null;
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

    @Override
    public boolean performTransaction(MarketTransaction offer) {
        return false;
    }
//    private final MarketDB marketDB;
//    private TransactionHandler transactionHandler;
//
//    public MarketHandler(MarketDB marketDB){
//        this.marketDB = marketDB;
//    }
//
//    public MarketHandler(){
//        this(Database.getInstance().getMarketDB());
//    }
//
//    @Override
//    public MarketTransaction getOffer(int index) {
//        return marketDB.getSingle(index);
//    }
//
//    @Override
//    public List<MarketTransaction> getOffers() {
//        return marketDB.getAll();
//    }
//
//    @Override
//    public List<MarketTransaction> getCardOffers() {
//        return marketDB.getCardOffers();
//    }
//
//    @Override
//    public List<MarketTransaction> getBundleOffers() {
//        return marketDB.getBundleOffers();
//    }
//
//    @Override
//    public List<MarketTransaction> getPackOffers() {
//        return marketDB.getPackOffers();
//    }
//
//    @Override
//    public boolean performTransaction(MarketTransaction offer) {
//        assert offer != null;
//        transactionHandler = new TransactionHandler(Database.getInstance().getCardInventory(),Database.getInstance().getPackInventory(), Database.getInstance().getCurrencyInventory());
//        return transactionHandler.performTransaction(offer);
//    }
}
