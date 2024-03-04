/**
 * MarketHandlerTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     03-March-2024
 *
 * PURPOSE:     Unit Test for MarketHandler
 */

package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;
import java.util.Random;

public class MarketHandlerTest {
    private IMarketHandler marketHandler;

    private IRegistry marketDB;

    private int numOfOffers;

    @Before
    public void setup() {
        //TODO waiting for marketDB implementation
        // marketDB = (implementation)
        marketHandler = new MarketHandler(marketDB);
        numOfOffers = marketDB.getAll().size();
    }

    @Test
    public void testMarketHandlerConstructor() {
        assert marketHandler != null;
    }

    @Test
    public void testGetOffer() {
        if (numOfOffers != 0) {
            Transaction temp = marketHandler.getOffer(0);
            assertEquals(marketDB.getSingle(0), temp);
            int RandIndex = new Random().nextInt(numOfOffers);
            temp = marketHandler.getOffer(RandIndex);
            assertEquals(marketDB.getSingle(RandIndex), temp);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOfferOutOfBound() {
        marketHandler.getOffer(numOfOffers + 1);
    }

    @Test
    public void testGetOffers() {
        int numOfGetting = marketHandler.getOffers().size();
        assertEquals(numOfOffers, numOfGetting);
    }

    @Test
    void testOffersContent() {
        List<Transaction> tempList = marketHandler.getOffers();
        List<Transaction> tempRegList = marketDB.getAll();
        for (Transaction i : tempList) {
            assert (tempRegList.contains(i));
        }
    }

    @Test
    public void testSelectOffer(){
        if(numOfOffers != 0){
            marketHandler.takeOffer(numOfOffers-1);
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSelectOfferOutOfBound(){
        marketHandler.takeOffer(numOfOffers);
    }

}
