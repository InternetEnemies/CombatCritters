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
import com.internetEnemies.combatCritters.data.OffersDatabase;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.List;

/**
 * MarketHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-21
 *
 * @PURPOSE:    Implementation of IMarketHandler
 */

public class MarketHandler implements IMarketHandler{
    private final IMarketDB marketDB;
    private final TransactionHandler transactionHandler;

    private final IMarketCycle marketCycle;

    public MarketHandler(IMarketDB marketDB, TransactionHandler transactionHandler){
        this.marketDB = marketDB;
        this.transactionHandler = transactionHandler;
        this.marketCycle = new MarketCycle(marketDB);
    }
    public MarketHandler(IMarketDB marketDB, TransactionHandler transactionHandler, IMarketCycle marketCycle){
        this.marketDB = marketDB;
        this.transactionHandler = transactionHandler;
        this.marketCycle = marketCycle;
    }

    public MarketHandler(){
        this(
                OffersDatabase.getInstance().getMarketDB(),
                new TransactionHandler(
                        Database.getInstance().getCardInventory(),
                        Database.getInstance().getPackInventory(),
                        Database.getInstance().getCurrencyInventory()
                )
        );
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

    @Override
    public void refreshDiscounts() {
        marketCycle.setCurrentTime();
        marketCycle.applyDiscounts();
    }

}
