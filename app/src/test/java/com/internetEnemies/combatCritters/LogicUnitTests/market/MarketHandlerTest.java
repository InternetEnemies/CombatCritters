/**
 * MarketHandlerTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     03-March-2024
 *
 * PURPOSE:     Unit Test for MarketHandler
 */

package com.internetEnemies.combatCritters.LogicUnitTests.market;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.market.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.market.MarketHandler;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.PackInventoryStub;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.data.MarketDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.IMarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.Logic.transaction.builders.MarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

public class MarketHandlerTest {
    private IMarketHandler marketHandler;

    private MarketDB marketDB;

    @Before
    public void setup() {
        marketDB = new MarketDB();
        IMarketTransactionBuilder offerBuilder = new MarketTransactionBuilder();
        CritterCard testCard = new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        Pack testPack = new Pack(0, "", "", new ArrayList<>(), new ArrayList<>());
        Currency testCurrency = new Currency(100);
        ItemStack<Card> testCardStack = new ItemStack<>(testCard, 2);
        ItemStack<Pack> testPackStack = new ItemStack<>(testPack, 1);

        //first offer 100 for card, no discount
        offerBuilder.setPrice(testCurrency);
        offerBuilder.addToReceived(testCardStack);
        offerBuilder.setDiscount(1.0);
        offerBuilder.setID(1);
        marketDB.addCardOffer(offerBuilder.build());
        offerBuilder.reset();

        //pack offer 50 for pack, 0.8 discount
        testCurrency = new Currency(50);
        offerBuilder.setPrice(testCurrency);
        offerBuilder.addToReceived(testPackStack);
        offerBuilder.setDiscount(0.8);
        offerBuilder.setID(2);
        marketDB.addPackOffer(offerBuilder.build());
        offerBuilder.reset();

        //bundle offer 200 for bundle, no discount
        testCurrency = new Currency(200);
        offerBuilder.setPrice(testCurrency);
        offerBuilder.addToReceived(testCardStack);
        offerBuilder.addToReceived(testPackStack);
        offerBuilder.setDiscount(0);
        offerBuilder.setID(3);
        marketDB.addBundleOffer(offerBuilder.build());
        offerBuilder.reset();

        //bundle offer 0 for bundle, no discount
        testCurrency = new Currency(0);
        offerBuilder.setPrice(testCurrency);
        offerBuilder.addToReceived(testCardStack);
        offerBuilder.addToReceived(testPackStack);
        offerBuilder.setDiscount(1.0);
        offerBuilder.setID(4);
        marketDB.addBundleOffer(offerBuilder.build());
        offerBuilder.reset();

        marketHandler = new MarketHandler(marketDB,new TransactionHandlerDeprecated(
                new CardInventoryStub(),
                new PackInventoryStub(),
                new CurrencyInventoryStub()
        ));
    }

    @Test
    public void testMarketHandlerConstructor() {
        assert marketHandler != null;
    }

    @Test
    public void testGetCardOffer() {
        List<MarketTransaction> temp = marketHandler.getCardOffers();
        assert temp.size() == 1;
        assert marketDB.getCardOffers().equals(temp);
    }

    @Test
    public void testGetPackOffer() {
        List<MarketTransaction> temp = marketHandler.getPackOffers();
        assert temp.size() == 1;
        assert marketDB.getPackOffers().equals(temp);
    }

    @Test
    public void testGetBundleOffer() {
        List<MarketTransaction> temp = marketHandler.getBundleOffers();
        assert temp.size() == 2;
        assert marketDB.getBundleOffers().equals(temp);
    }

    @Test
    public void testPerformTransactionFalse(){
        List<MarketTransaction> tempList = marketHandler.getBundleOffers();
        MarketTransaction tempOffer = tempList.get(0);
        assertFalse(marketHandler.performTransaction(tempOffer));
    }

    @Test
    public void testPerformTransactionTrue(){
        List<MarketTransaction> tempList = marketHandler.getBundleOffers();
        MarketTransaction tempOffer = tempList.get(1);
        assertTrue(marketHandler.performTransaction(tempOffer));
    }

    @Test (expected = AssertionError.class)
    public void testPerformTransactionNull(){
        marketHandler.performTransaction(null);
    }

}
