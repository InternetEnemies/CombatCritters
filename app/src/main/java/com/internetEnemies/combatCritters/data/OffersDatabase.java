/**
 * OffersDatabase.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    A fake Database for offers in marketDB and TradeRegistry
 *              relies on PackCardDatabase
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IMarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.IPackBuilder;
import com.internetEnemies.combatCritters.objects.ITradeTransactionBuilder;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.MarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.PackBuilder;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.TradeTransactionBuilder;

import java.util.ArrayList;
import java.util.List;

public class OffersDatabase {
    private static OffersDatabase INSTANCE;

    private final TradeRegistry tradesDB;
    private final MarketDB marketDB;

    public static OffersDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OffersDatabase();
        }
        return INSTANCE;
    }

    public TradeRegistry getTradesDB() {
        return tradesDB;
    }

    public MarketDB getMarketDB() {
        return marketDB;
    }

    private OffersDatabase() {
        IRegistry<Pack> packDB = PackCardDatabase.getInstance().getPackDB();
        IRegistry<Card> cardDB = PackCardDatabase.getInstance().getCardDB();
        List<TradeTransaction> testTradeOffer = new ArrayList<TradeTransaction>();
        List<MarketTransaction> testMarketCardOffer = new ArrayList<MarketTransaction>();
        List<MarketTransaction> testMarketPackOffer = new ArrayList<MarketTransaction>();
        List<MarketTransaction> testMarketBundleOffer = new ArrayList<MarketTransaction>();
        IMarketTransactionBuilder marketBuilder = new MarketTransactionBuilder();
        ITradeTransactionBuilder tradesBuilder = new TradeTransactionBuilder();
        IPackBuilder packBuilder = new PackBuilder();
        Currency currency = new Currency(100);
        float discount = 1;

        // making the instance
        // 10 Market offers

        // 10 Trade offers


        tradesDB = new TradeRegistry(testTradeOffer);
        marketDB = new MarketDB(testMarketBundleOffer,testMarketCardOffer,testMarketPackOffer);
    }
}
