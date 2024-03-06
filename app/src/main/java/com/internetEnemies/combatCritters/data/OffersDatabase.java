/**
 * OffersDatabase.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    A fake Database for offers in marketDB and TradeRegistry
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.TradeTransaction;

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
        List<TradeTransaction> testTradeOffer = new ArrayList<TradeTransaction>();
        List<MarketTransaction> testMarketOffer = new ArrayList<MarketTransaction>();

        // make the instance


        tradesDB = new TradeRegistry(testTradeOffer);
        marketDB = new MarketDB(testMarketOffer);
    }
}
