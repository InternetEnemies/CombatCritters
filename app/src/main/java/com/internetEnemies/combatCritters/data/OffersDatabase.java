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

import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.IMarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ITradeTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.MarketTransactionBuilder;
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
        List<TradeTransaction> testTradeOffer = new ArrayList<>();
        List<MarketTransaction> testMarketCardOffer = new ArrayList<>();
        List<MarketTransaction> testMarketPackOffer = new ArrayList<>();
        List<MarketTransaction> testMarketBundleOffer = new ArrayList<>();
        IMarketTransactionBuilder marketBuilder = new MarketTransactionBuilder();
        ITradeTransactionBuilder tradesBuilder = new TradeTransactionBuilder();
        Currency testcurrency = new Currency(100);
        double discount = 1;

        // making the instance
        // 10 Market offers (3 pack, 4 card, 3 bundle)
        // 3 pack offers
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack<>(PackCardDatabase.getInstance().getPackDB().getSingle(0),1));
        testMarketPackOffer.add(marketBuilder.build());
        marketBuilder.reset();

        testcurrency = new Currency(50);
        discount = 0.5;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack(PackCardDatabase.getInstance().getPackDB().getSingle(1),1));
        testMarketPackOffer.add(marketBuilder.build());
        marketBuilder.reset();

        testcurrency = new Currency(30);
        discount = 0;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack(PackCardDatabase.getInstance().getPackDB().getSingle(2),1));
        testMarketPackOffer.add(marketBuilder.build());
        marketBuilder.reset();

        // 4 card offers
        testcurrency = new Currency(50);
        discount = 1;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(5),1));
        testMarketCardOffer.add(marketBuilder.build());
        marketBuilder.reset();

        testcurrency = new Currency(50);
        discount = 0.7;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(29),1));
        testMarketCardOffer.add(marketBuilder.build());
        marketBuilder.reset();

        testcurrency = new Currency(50);
        discount = 0.5;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(45),1));
        testMarketCardOffer.add(marketBuilder.build());
        marketBuilder.reset();

        testcurrency = new Currency(50);
        discount = 0.5;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(46),1));
        testMarketCardOffer.add(marketBuilder.build());
        marketBuilder.reset();

        // 3 bundle offers
        testcurrency = new Currency(100);
        discount = 0.5;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(45),1));
        marketBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(46),1));
        testMarketBundleOffer.add(marketBuilder.build());
        marketBuilder.reset();

        testcurrency = new Currency(100);
        discount = 0.8;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(45),1));
        marketBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getPackDB().getSingle(2),1));
        testMarketBundleOffer.add(marketBuilder.build());
        marketBuilder.reset();

        testcurrency = new Currency(50);
        discount = 0.8;
        marketBuilder.setPrice(testcurrency);
        marketBuilder.setDiscount(discount);
        marketBuilder.addToReceived(new ItemStack(PackCardDatabase.getInstance().getPackDB().getSingle(1),1));
        marketBuilder.addToReceived(new ItemStack(PackCardDatabase.getInstance().getPackDB().getSingle(2),1));
        testMarketBundleOffer.add(marketBuilder.build());
        marketBuilder.reset();

        // 5 Trade offers
        tradesBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(44),1));
        tradesBuilder.addToGiven(new ItemStack<IItem>(testcurrency));
        testTradeOffer.add((TradeTransaction) tradesBuilder.build());
        tradesBuilder.reset();

        testcurrency = new Currency(200);
        tradesBuilder.addToReceived(new ItemStack<IItem>(testcurrency));
        testTradeOffer.add((TradeTransaction) tradesBuilder.build());
        tradesBuilder.reset();

        tradesBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(30),1));
        tradesBuilder.addToGiven(new ItemStack<IItem>(PackCardDatabase.getInstance().getPackDB().getSingle(0),1));
        testTradeOffer.add((TradeTransaction) tradesBuilder.build());
        tradesBuilder.reset();

        tradesBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(44),1));
        tradesBuilder.addToGiven(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(40),1));
        testTradeOffer.add((TradeTransaction) tradesBuilder.build());
        tradesBuilder.reset();

        tradesBuilder.addToReceived(new ItemStack<IItem>(PackCardDatabase.getInstance().getCardDB().getSingle(44),1));
        tradesBuilder.addToGiven(new ItemStack<IItem>(PackCardDatabase.getInstance().getPackDB().getSingle(2),1));
        testTradeOffer.add((TradeTransaction) tradesBuilder.build());
        tradesBuilder.reset();

        tradesDB = new TradeRegistry(testTradeOffer);
        marketDB = new MarketDB(testMarketBundleOffer,testMarketCardOffer,testMarketPackOffer);
    }
}
