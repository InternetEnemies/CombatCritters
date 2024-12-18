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

package com.internetEnemies.combatCritters.Logic.market;

import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.market.IMarketDB;
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

@Deprecated
public class MarketHandler implements IMarketHandler{

    private final IMarketDB marketDB;
    private final TransactionHandlerDeprecated transactionHandler;

    private final IMarketCycleDeprecated marketCycle;

    public MarketHandler(IMarketDB marketDB, TransactionHandlerDeprecated transactionHandler){
        this(marketDB, transactionHandler, new MarketCycleDeprecated(marketDB));

    }
    public MarketHandler(IMarketDB marketDB, TransactionHandlerDeprecated transactionHandler, IMarketCycleDeprecated marketCycle){
        this.marketDB = marketDB;
        this.transactionHandler = transactionHandler;
        this.marketCycle = marketCycle;
    }

    public MarketHandler(){
        this(
                OffersDatabase.getInstance().getMarketDB(),
                new TransactionHandlerDeprecated(
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
