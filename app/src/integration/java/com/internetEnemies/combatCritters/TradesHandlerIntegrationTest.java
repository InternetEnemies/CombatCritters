/**
 * TradesHandlerTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     29-February-2024
 *
 * PURPOSE:     Integration Tests for TradeHandler
 */

package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.trading.ITradesHandler;
import com.internetEnemies.combatCritters.Logic.transaction.builders.TradeTransactionBuilder;
import com.internetEnemies.combatCritters.Logic.trading.TradesHandler;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryPackHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.TradeTransactionRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.TransactionRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TradesHandlerIntegrationTest {

    private ITradesHandler tradesHandler;

    private IRegistry<TradeTransaction> tradeRegistry;

    private int numOfOffers;
    private int sampleTradeId;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        TradeTransactionRegistryHSQLDB tradeRegistry = new TradeTransactionRegistryHSQLDB(path, new TransactionRegistryHSQLDB(path));
        RegistryPackHSQLDB packReg = new RegistryPackHSQLDB(path);
        RegistryCardHSQLDB cardReg = new RegistryCardHSQLDB(path);

        ITradeTransactionBuilder offerBuilder = new TradeTransactionBuilder();

        Card testCard = cardReg.add(new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON, 0, 0, null));
        Pack testPack = packReg.add(new Pack(0, "", "", new ArrayList<>(), new ArrayList<>()));
        Currency testCurrency = new Currency(100);

        ItemStack<Card> testCardStack = new ItemStack<>(testCard, 2);
        ItemStack<Pack> testPackStack = new ItemStack<>(testPack, 1);
        ItemStack<Currency> testCurrencyStack = new ItemStack<>(testCurrency, 1);

        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testCardStack);
        offerBuilder.setID(-1);
        tradeRegistry.add(offerBuilder.build());

        offerBuilder.reset();
        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testPackStack);
        MultiReceiveTransaction transaction = tradeRegistry.add(offerBuilder.build());
        sampleTradeId = transaction.getId();

        offerBuilder.reset();
        offerBuilder.addToReceived(testCurrencyStack);
        offerBuilder.addToGiven(testPackStack);
        offerBuilder.addToGiven(testCardStack);
        tradeRegistry.add(offerBuilder.build());

        User dummy = TestUtils.getDummyUser(path);
        tradesHandler = new TradesHandler(
                tradeRegistry,
                new TransactionHandlerDeprecated(
                        new CardInventoryHSQLDB(path, dummy),
                        new PackInventoryHSQLDB(path, dummy),
                        new CurrencyInventoryHSQLDB(path, dummy)
                ));
        numOfOffers = tradeRegistry.getAll().size();
        //three offers
        // card, pack, bundle
        this.tradeRegistry = tradeRegistry;
    }

    @Test
    public void testTradesHandlerConstructor(){
        assert tradesHandler != null;
    }

    @Test
    public void testGetOffer(){
        if(numOfOffers != 0){
            TradeTransaction temp = tradesHandler.getOffer(1);
            assertEquals(tradeRegistry.getSingle(1),temp);
            int RandIndex = new Random().nextInt(numOfOffers);
            temp = tradesHandler.getOffer(RandIndex);
            assertEquals(tradeRegistry.getSingle(RandIndex),temp);
        }
    }

    @Test
    public void testGetOfferOutOfBound(){
        assertNull(tradesHandler.getOffer(-1));
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
        assertFalse(tradesHandler.performTransaction(tradesHandler.getOffer(sampleTradeId)));
    }

    @Test (expected = AssertionError.class)
    public void testPerformTransactionNull(){
        tradesHandler.performTransaction(null);
    }
}
