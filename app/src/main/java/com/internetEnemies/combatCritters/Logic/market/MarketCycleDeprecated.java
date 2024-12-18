package com.internetEnemies.combatCritters.Logic.market;


import com.internetEnemies.combatCritters.data.market.IMarketDB;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
/**
 * MarketCycle.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-21
 *
 * @PURPOSE:    Implementation of IMarketCycle
 */
@Deprecated
public class MarketCycleDeprecated implements IMarketCycleDeprecated {

    public final int NUM_DISCOUNTS = 3;
    public final double MAX_DISCOUNT_VAL = 0.85;
    public final double MIN_DISCOUNT_VAL = 0.1;

    private final int hours;

    private final int mins;
    private LocalDateTime refreshTime;
    private final IMarketDB marketDB;
    private LocalDateTime currentTime;


    public MarketCycleDeprecated(int hours, int mins, IMarketDB marketDB){
        this.mins = mins;
        this.hours = hours;
        refreshTime = LocalDateTime.now().with(LocalTime.of(this.hours, this.mins));
        this.marketDB = marketDB;
    }
    public MarketCycleDeprecated(IMarketDB marketDB){
        this(LocalTime.MIDNIGHT.getHour(), LocalTime.MIDNIGHT.getMinute(), marketDB);
    }
    @Override
    public void applyDiscounts(Map<Integer, Double> discounts) {
        if(currentTime.isAfter(refreshTime)){
            marketDB.adjustDiscount(discounts);
            refreshTime = LocalDateTime.now().with(LocalTime.of(this.hours, this.mins));
        }
    }

    @Override
    public void setCurrentTime(){
        currentTime = LocalDateTime.now();
    }
    @Override
    public void setCurrentTime(LocalDateTime time){
        currentTime = time;
    }
    @Override
    public void applyDiscounts() {
        Map<Integer, Double> discounts = generateDiscounts(NUM_DISCOUNTS);
        applyDiscounts(discounts);
    }

    /**
     * Helper function to generate random ids and discounts in a certain range.
     * @param numDiscounts the number of discounts to be changed
     * @return a map of ids as the key a discounts as the value.
     */
    public Map<Integer, Double> generateDiscounts(int numDiscounts) {
        List<MarketTransaction> allTransactions = marketDB.getAllOffers();
        Map<Integer, Double> adjustedDiscounts = new TreeMap<>();
        Random random = new Random();
        random.setSeed((long) this.currentTime.getDayOfMonth() << 32 | this.currentTime.getMonthValue());

        for (int i = 0; i < numDiscounts; i++)
        {
            // generating the index using Math.random()
            int index = (int)(random.nextDouble() * allTransactions.size());
            double discount = MIN_DISCOUNT_VAL + random.nextDouble() * (MAX_DISCOUNT_VAL - MIN_DISCOUNT_VAL);
            adjustedDiscounts.put(allTransactions.get(index).getId(),discount);
            allTransactions.remove(index);
        }

        return adjustedDiscounts;
    }

}
