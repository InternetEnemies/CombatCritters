package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IMarketDB;
import com.internetEnemies.combatCritters.data.MarketDB;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * MarketCycle.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-21
 *
 * @PURPOSE:    Implementation of IMarketCycle
 */
public class MarketCycle implements IMarketCycle{

    public final int NUM_DISCOUNTS = 3;
    public final double MAX_DISCOUNT_VAL = 0.85;
    public final double MIN_DISCOUNT_VAL = 0.1;

    private final int hours;

    private final int mins;
    private LocalDateTime refreshTime;
    private final IMarketDB marketDB;
    private LocalDateTime currentTime;

    public MarketCycle(IMarketDB marketDB){
        hours = 23;
        mins = 59;
        refreshTime = LocalDateTime.now().with(LocalTime.of(hours, mins));
        this.marketDB = marketDB;
    }

    public MarketCycle(int hours, int mins, IMarketDB marketDB){
        this.mins = mins;
        this.hours = hours;
        refreshTime = LocalDateTime.now().with(LocalTime.of(this.hours, this.mins));
        this.marketDB = marketDB;
    }
    @Override
    public void applyDiscounts(Map<Integer, Double> discounts) {
        if(currentTime.isAfter(refreshTime)){
            marketDB.adjustDiscount(discounts);
            refreshTime = LocalDateTime.now().with(LocalTime.of(this.hours, this.mins));
        }
    }

    public void setCurrentTime(){
        currentTime = LocalDateTime.now();
    }

    public void applyDiscounts() {
        Map<Integer, Double> discounts = generateDiscounts(NUM_DISCOUNTS);
        applyDiscounts(discounts);
    }

    @Override
    public Map<Integer, Double> generateDiscounts(int numDiscounts) {
        List<MarketTransaction> allTransactions = new ArrayList<>();
        Map<Integer, Double> adjustedDiscounts = new TreeMap<>();

        allTransactions.addAll(marketDB.getBundleOffers());
        allTransactions.addAll(marketDB.getCardOffers());
        allTransactions.addAll(marketDB.getPackOffers());

        for (int i = 0; i < numDiscounts; i++)
        {
            // generating the index using Math.random()
            int index = (int)(Math.random() * allTransactions.size());
            double discount = MIN_DISCOUNT_VAL + Math.random() * (MAX_DISCOUNT_VAL - MIN_DISCOUNT_VAL);
            adjustedDiscounts.put(index,discount);
            allTransactions.remove(index);
        }

        return adjustedDiscounts;
    }

}
