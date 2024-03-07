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
import com.internetEnemies.combatCritters.data.TradeRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ITradeTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.TradeTransactionBuilder;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;
import java.util.Random;

public class TradesHandlerTest {

    private ITradesHandler tradesHandler;

    private TradeRegistry tradeRegistry;

    private int numOfOffers;
    @Before
    public void setup(){
        tradeRegistry = new TradeRegistry();
        ITradeTransactionBuilder offerBuilder = new TradeTransactionBuilder();
        CritterCard testCard = new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        Pack testPack = new Pack(0, "", "", null, null);
        Currency testCurrency = new Currency(100);
        ItemStack<Card> testCardStack = new ItemStack<>(testCard, 2);
        ItemStack<Pack> testPackStack = new ItemStack<>(testPack, 1);
        ItemStack<Currency> testCurrencyStack = new ItemStack<>(testCurrency, 1);
        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testCardStack);
        tradeRegistry.add((TradeTransaction) offerBuilder.build());
        offerBuilder.reset();
        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testPackStack);
        tradeRegistry.add((TradeTransaction) offerBuilder.build());
        offerBuilder.reset();
        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testPackStack);
        offerBuilder.addToGiven(testCardStack);
        tradeRegistry.add((TradeTransaction) offerBuilder.build());
        tradesHandler = new TradesHandler(tradeRegistry);
        numOfOffers = tradeRegistry.getAll().size();
        //three offers
        // card, pack, mix
    }

    @Test
    public void testTradesHandlerConstructor(){
        assert tradesHandler != null;
    }

    @Test
    public void testGetOffer(){
        if(numOfOffers != 0){
            TradeTransaction temp = tradesHandler.getOffer(0);
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

    @Test
    public void testOffersContent(){
        List<TradeTransaction> tempList = tradesHandler.getOffers();
        List<Transaction> tempRegList = tradeRegistry.getAll();
        for(Transaction i: tempList){
            assert(tempRegList.contains(i));
        }
    }

    @Test
    public void testSelectOffer(){
        if(numOfOffers != 0){
            //tradesHandler.visitOffer(numOfOffers-1);
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSelectOfferOutOfBound(){
        //tradesHandler.visitOffer(numOfOffers);
    }
}
