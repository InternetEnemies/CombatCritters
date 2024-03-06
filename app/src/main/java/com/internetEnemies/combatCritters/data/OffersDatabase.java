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
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
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
        IRegistry<Pack> packDB = PackCardDatabase.getInstance().getPackDB();
        IRegistry<Card> cardDB = PackCardDatabase.getInstance().getCardDB();
        List<TradeTransaction> testTradeOffer = new ArrayList<TradeTransaction>();
        List<MarketTransaction> testMarketOffer = new ArrayList<MarketTransaction>();

        // make the instance
        // 10 Market offers
        // 10 Trade offers


        tradesDB = new TradeRegistry(testTradeOffer);
        marketDB = new MarketDB(testMarketOffer);
    }
}
