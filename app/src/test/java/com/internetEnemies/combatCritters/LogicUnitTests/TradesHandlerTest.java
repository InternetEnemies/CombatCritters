/**
 * TradesHandlerTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     29-February-2024
 *
 * PURPOSE:     Unit Test for TradeHandler
 */

package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.internetEnemies.combatCritters.Logic.ITradesHandler;
import com.internetEnemies.combatCritters.Logic.TradesHandler;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;
import java.util.Random;

public class TradesHandlerTest {

    private ITradesHandler tradesHandler;

    private IRegistry tradeRegistry;

    private int numOfOffers;
    @Before
    public void setup(){
        //TODO waiting for tradeRegistry
        // tradeRegistry = (implementation)
        tradesHandler = new TradesHandler(tradeRegistry);
        numOfOffers = tradeRegistry.getAll().size();
    }

    @Test
    public void testTradesHandlerConstructor(){
        assert tradesHandler != null;
    }

    @Test
    public void testGetOffer(){
        if(numOfOffers != 0){
            Transaction temp = tradesHandler.getOffer(0);
            assertEquals(tradeRegistry.getSingle(0),temp);
            int RandIndex = new Random().nextInt(numOfOffers);
            temp = tradesHandler.getOffer(RandIndex);
            assertEquals(tradeRegistry.getSingle(RandIndex),temp);
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetOfferOutOfBound(){
        tradesHandler.getOffer(numOfOffers+1);
    }

    @Test
    public void testGetOffers(){
        int numOfGetting = tradesHandler.getOffers().size();
        assertEquals(numOfOffers,numOfGetting);
    }

    @Test void testOffersContent(){
        List<Transaction> tempList = tradesHandler.getOffers();
        List<Transaction> tempRegList = tradeRegistry.getAll();
        for(Transaction i: tempList){
            assert(tempRegList.contains(i));
        }
    }

    @Test
    public void testSelectOffer(){
        if(numOfOffers != 0){
            tradesHandler.selectOffer(numOfOffers-1);
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSelectOfferOutOfBound(){
        tradesHandler.selectOffer(numOfOffers);
    }
}
