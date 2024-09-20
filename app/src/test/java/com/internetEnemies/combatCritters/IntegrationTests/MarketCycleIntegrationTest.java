package com.internetEnemies.combatCritters.IntegrationTests;

import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.assertNotEquals;

import com.internetEnemies.combatCritters.Logic.market.IMarketCycle;
import com.internetEnemies.combatCritters.Logic.market.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.market.MarketCycle;
import com.internetEnemies.combatCritters.Logic.market.MarketHandler;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.IMarketDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.MarketRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;

import com.internetEnemies.combatCritters.data.hsqldb.TransactionRegistryHSQLDB;

import com.internetEnemies.combatCritters.objects.MarketTransaction;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MarketCycleIntegrationTest {
    private IMarketHandler marketHandler;
    private IMarketCycle marketCycle;

    private IMarketDB marketDB;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        MarketRegistryHSQLDB marketDB = new MarketRegistryHSQLDB(path, new TransactionRegistryHSQLDB(path));

        this.marketDB = marketDB;
        marketHandler = new MarketHandler(marketDB, new TransactionHandler(
                new CardInventoryHSQLDB(path),
                new PackInventoryHSQLDB(path),
                new CurrencyInventoryHSQLDB(path)
        ));
        marketCycle = new MarketCycle(4, 20, marketDB);
    }
    @Test
    public void testBeforeRefresh(){
        //should do nothing
        Map<Integer, Double> discounts = new TreeMap<>();
        discounts.put(1, 0.5);
        discounts.put(4, 0.1);
        marketCycle.setCurrentTime(LocalDateTime.of(1984, 1, 1, 1, 1));

        List<MarketTransaction> oldOffers = new ArrayList<>();
        oldOffers.addAll(marketHandler.getCardOffers());
        oldOffers.addAll(marketHandler.getPackOffers());
        oldOffers.addAll(marketHandler.getBundleOffers());

        marketCycle.applyDiscounts(discounts);

        List<MarketTransaction> newOffers = new ArrayList<>();
        newOffers.addAll(marketHandler.getCardOffers());
        newOffers.addAll(marketHandler.getPackOffers());
        newOffers.addAll(marketHandler.getBundleOffers());

        for(int i = 0; i < newOffers.size(); i++){
            assertEquals(newOffers.get(i).getDiscount(), oldOffers.get(i).getDiscount());
        }

    }
    @Test
    public void testAfterRefresh(){
        Map<Integer, Double> discounts = new TreeMap<>();
        discounts.put(1, 0.5);
        discounts.put(4, 0.1);
        marketCycle.setCurrentTime(LocalDateTime.of(3005, 1, 1, 1, 1));

        List<MarketTransaction> oldOffers = new ArrayList<>();
        oldOffers.addAll(marketHandler.getCardOffers());
        oldOffers.addAll(marketHandler.getPackOffers());
        oldOffers.addAll(marketHandler.getBundleOffers());

        marketCycle.applyDiscounts(discounts);

        List<MarketTransaction> newOffers = new ArrayList<>();
        newOffers.addAll(marketHandler.getCardOffers());
        newOffers.addAll(marketHandler.getPackOffers());
        newOffers.addAll(marketHandler.getBundleOffers());

        for(int i = 0; i < newOffers.size(); i++){
            for (Map.Entry<Integer, Double> discount: discounts.entrySet()) {
                if (discount.getKey() == newOffers.get(i).getId()){
                    assertNotEquals(newOffers.get(i).getDiscount(), oldOffers.get(i).getDiscount());
                }

            }
        }

    }
}
