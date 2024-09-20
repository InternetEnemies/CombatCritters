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
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.PackInventoryStub;
import com.internetEnemies.combatCritters.data.Registry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ITradeTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.Logic.transaction.builders.TradeTransactionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TradesHandlerTest {

    private ITradesHandler tradesHandler;

    private Registry<TradeTransaction> tradeRegistry;

    private int numOfOffers;
    @Before
    public void setup(){
        tradeRegistry = new Registry<>();
        ITradeTransactionBuilder offerBuilder = new TradeTransactionBuilder();
        CritterCard testCard = new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        Pack testPack = new Pack(0, "", "", new ArrayList<>(), new ArrayList<>());
        Currency testCurrency = new Currency(100);
        ItemStack<Card> testCardStack = new ItemStack<>(testCard, 2);
        ItemStack<Pack> testPackStack = new ItemStack<>(testPack, 1);
        ItemStack<Currency> testCurrencyStack = new ItemStack<>(testCurrency, 1);

        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testCardStack);
        offerBuilder.setID(1);
        tradeRegistry.add(offerBuilder.build());

        offerBuilder.reset();
        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testPackStack);
        offerBuilder.setID(2);
        tradeRegistry.add(offerBuilder.build());

        offerBuilder.reset();
        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testPackStack);
        offerBuilder.addToGiven(testCardStack);
        offerBuilder.setID(3);
        tradeRegistry.add(offerBuilder.build());

        tradesHandler = new TradesHandler(
                tradeRegistry,
                new TransactionHandler(
                        new CardInventoryStub(),
                        new PackInventoryStub(),
                        new CurrencyInventoryStub()
                ));
        numOfOffers = tradeRegistry.getAll().size();
        //three offers
        // card, pack, bundle
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
        List<TradeTransaction> tempRegList = tradeRegistry.getAll();
        for(TradeTransaction i: tempList){
            assert(tempRegList.contains(i));
        }
    }

    @Test
    public void testPerformTransaction(){
        assertFalse(tradesHandler.performTransaction(tradesHandler.getOffer(0)));
    }

    @Test (expected = AssertionError.class)
    public void testPerformTransactionNull(){
        tradesHandler.performTransaction(null);
    }
}
