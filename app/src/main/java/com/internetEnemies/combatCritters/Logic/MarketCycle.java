package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IMarketDB;
import com.internetEnemies.combatCritters.data.MarketDB;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
//TODO: Maybe switch to datetime?
public class MarketCycle implements IMarketCycle{

    public final int NUM_DISCOUNTS = 3;

    private final LocalTime refreshTime;
    private final IMarketDB marketDB;

    public MarketCycle(IMarketDB marketDB){
        refreshTime = LocalTime.of(LocalTime.MIDNIGHT.getHour(), LocalTime.MIDNIGHT.getMinute()); //should refresh at midnight GMT
        this.marketDB = marketDB;
    }

    public MarketCycle(int hours, int mins, IMarketDB marketDB){
        refreshTime = LocalTime.of(hours, mins);
        this.marketDB = marketDB;
    }
    @Override
    public void applyDiscounts(Map<Integer, Double> discounts) {
        LocalTime currentTime = LocalTime.now();

        if(currentTime.isAfter(refreshTime)){
            marketDB.adjustDiscount(discounts);
        }
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


        }





    }

}
