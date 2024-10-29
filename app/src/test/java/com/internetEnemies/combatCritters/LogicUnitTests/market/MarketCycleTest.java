package com.internetEnemies.combatCritters.LogicUnitTests.market;
import static junit.framework.TestCase.assertEquals;

import com.internetEnemies.combatCritters.Logic.market.IMarketCycle;
import com.internetEnemies.combatCritters.Logic.market.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.market.MarketCycle;
import com.internetEnemies.combatCritters.Logic.market.MarketHandler;
import com.internetEnemies.combatCritters.Logic.transaction.builders.MarketTransactionBuilder;
import com.internetEnemies.combatCritters.data.market.MarketDB;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Ignore // this has 1 test, and it's going to have to be rewritten for the rework so ignore for now see 
public class MarketCycleTest {
    IMarketCycle marketCycle;
    IMarketHandler marketHandler;
    MarketDB marketDB;
    @Before
    public void setup(){
        marketDB = new MarketDB();
        marketHandler = new MarketHandler();

        MarketTransactionBuilder builder = new MarketTransactionBuilder();
        builder.setID(0);
        builder.setPrice(new Currency(100));
        builder.setDiscount(0);
        marketDB.addPackOffer(builder.build());

        builder.reset();

        builder.setID(1);
        builder.setPrice(new Currency(100));
        builder.setDiscount(0.40);
        marketDB.addCardOffer(builder.build());

        builder.reset();

        builder.setID(2);
        builder.setPrice(new Currency(100));
        builder.setDiscount(0.40);
        marketDB.addBundleOffer(builder.build());

        builder.reset();
    }
    @Test
    public void testTimeCycle(){
        marketCycle = new MarketCycle(4, 20, marketDB);
        Map<Integer, Double> discounts = new TreeMap<>();
        discounts.put(1, 0.5);
        List<MarketTransaction> offers = marketDB.getCardOffers();
        marketCycle.setCurrentTime(LocalDateTime.of(1945, 1, 1, 1, 1));

        marketCycle.applyDiscounts(discounts);
        assertEquals(offers.get(0).getDiscount(), 0.4);

        marketCycle.setCurrentTime(LocalDateTime.of(3000, 1, 1, 1, 1));

        marketCycle.applyDiscounts(discounts);
        offers = marketDB.getCardOffers();
        assertEquals(offers.get(0).getDiscount(), 0.5);
    }

}
