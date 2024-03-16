/**
 * MarketHandlerTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     03-March-2024
 *
 * PURPOSE:     Unit Test for MarketHandler
 */

package com.internetEnemies.combatCritters.IntegrationTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.Logic.MarketTransactionBuilder;
import com.internetEnemies.combatCritters.Logic.TransactionHandler;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.IMarketDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.MarketRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryPackHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.TransactionRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IMarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarketHandlerIntegrationTest {
    private IMarketHandler marketHandler;

    private IMarketDB marketDB;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        MarketRegistryHSQLDB marketDB = new MarketRegistryHSQLDB(path, new TransactionRegistryHSQLDB(path));

        RegistryPackHSQLDB packReg = new RegistryPackHSQLDB(path);
        RegistryCardHSQLDB cardReg = new RegistryCardHSQLDB(path);

        IMarketTransactionBuilder offerBuilder = new MarketTransactionBuilder();

        Card testCard = cardReg.addCard(new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null));
        Pack testPack = packReg.addPack(new Pack(0, "", "", new ArrayList<>(), new ArrayList<>()));
        Currency testCurrency = new Currency(100);

        ItemStack<Card> testCardStack = new ItemStack<>(testCard, 2);
        ItemStack<Pack> testPackStack = new ItemStack<>(testPack, 1);

        //first offer 100 for card, no discount
        offerBuilder.setPrice(testCurrency);
        offerBuilder.addToReceived(testCardStack);
        offerBuilder.setDiscount(1.0);
        marketDB.addCardOffer(offerBuilder.build());
        offerBuilder.reset();
        //pack offer 50 for pack, 0.8 discount
        testCurrency = new Currency(50);
        offerBuilder.setPrice(testCurrency);
        offerBuilder.addToReceived(testPackStack);
        offerBuilder.setDiscount(0.8);
        marketDB.addPackOffer(offerBuilder.build());
        offerBuilder.reset();
        //bundle offer 200 for bundle, no discount
        testCurrency = new Currency(200);
        offerBuilder.setPrice(testCurrency);
        offerBuilder.addToReceived(testCardStack);
        offerBuilder.addToReceived(testPackStack);
        offerBuilder.setDiscount(0);
        marketDB.addBundleOffer(offerBuilder.build());
        offerBuilder.reset();
        //bundle offer 0 for bundle, no discount
        testCurrency = new Currency(0);
        offerBuilder.setPrice(testCurrency);
        offerBuilder.addToReceived(testCardStack);
        offerBuilder.addToReceived(testPackStack);
        offerBuilder.setDiscount(1.0);
        marketDB.addBundleOffer(offerBuilder.build());
        offerBuilder.reset();

        this.marketDB = marketDB;
        marketHandler = new MarketHandler(marketDB, new TransactionHandler(
                new CardInventoryHSQLDB(path),
                new PackInventoryHSQLDB(path),
                new CurrencyInventoryStub()// todo
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
        assert marketDB.getCardOffers().containsAll(temp);
    }

    @Test
    public void testGetPackOffer() {
        List<MarketTransaction> temp = marketHandler.getPackOffers();
        assert temp.size() == 1;
        assert marketDB.getPackOffers().containsAll(temp);
    }

    @Test
    public void testGetBundleOffer() {
        List<MarketTransaction> temp = marketHandler.getBundleOffers();
        assert temp.size() == 2;
        assert marketDB.getBundleOffers().containsAll(temp);
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
