package com.internetEnemies.combatCritters.LogicUnitTests;

import com.internetEnemies.combatCritters.Logic.transaction.TransactionAdd;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionRemove;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.data.PackInventoryStub;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.Logic.transaction.builders.TradeTransactionBuilder;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

public class TransactionUnitTest {

    private ICurrencyInventory currencyInventory;
    private IPackInventory packInventory;
    private ICardInventory cardInventory;

    @Before
    public void setup(){
        currencyInventory = new CurrencyInventoryStub();
        currencyInventory.setBalance(new Currency(0));
        packInventory = new PackInventoryStub();
        cardInventory = new CardInventoryStub();
    }
    @Test
    public void testTransactionAdd(){
        CritterCard testCard = new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        Pack testPack = new Pack(0, "", "", new ArrayList<>(), new ArrayList<>());
        Currency testCurrency = new Currency(100);

        ItemStack<Card> testCardStack = new ItemStack<>(testCard, 2);
        ItemStack<Pack> testPackStack = new ItemStack<>(testPack, 1);
        ItemStack<Currency> testCurrencyStack = new ItemStack<>(testCurrency, 1);

        List<ItemStack<?>> addedItems = new ArrayList<>();
        addedItems.add(testCardStack);
        addedItems.add(testPackStack);
        addedItems.add(testCurrencyStack);

        for (ItemStack<?> item: addedItems) {
            TransactionAdd adder = new TransactionAdd(cardInventory, packInventory, currencyInventory, item.getAmount());
            item.getItem().accept(adder);
        }


        assertEquals(cardInventory.getCardAmount(testCard), 2);
        assertEquals(packInventory.getPackAmount(testPack), 1);
        assertEquals(currencyInventory.getCurrentBalance().getAmount(), 100);
    }
    @Test
    public void testRemove(){
        CritterCard testCard = new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        CritterCard testCard2 = new CritterCard(1, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        Pack testPack = new Pack(0, "", "", new ArrayList<>(), new ArrayList<>());

        ItemStack<Card> testCardStack = new ItemStack<>(testCard, 2);
        ItemStack<Card> testCardStack2 = new ItemStack<>(testCard2, 2);
        ItemStack<Pack> testPackStack = new ItemStack<>(testPack, 2);
        ItemStack<Currency> testCurrencyStack = new ItemStack<>(new Currency(80));

        currencyInventory.setBalance(new Currency(100));

        packInventory.addPack(testPack);
        packInventory.addPack(testPack);
        packInventory.addPack(testPack);
        packInventory.addPack(testPack);

        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);

        cardInventory.addCard(testCard2);
        cardInventory.addCard(testCard2);

        List<ItemStack<?>> removedItems = new ArrayList<>();
        removedItems.add(testCardStack);
        removedItems.add(testCardStack2);
        removedItems.add(testCurrencyStack);
        removedItems.add(testPackStack);

        for (ItemStack<?> item: removedItems) {
            TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, currencyInventory, item.getAmount());
            item.getItem().accept(remover);
        }

        assertEquals(currencyInventory.getCurrentBalance().getAmount(), 20);
        assertEquals(cardInventory.getCardAmount(testCard), 1);
        assertEquals(cardInventory.getCardAmount(testCard2), 0);
        assertEquals(packInventory.getPackAmount(testPack), 2);

        TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, currencyInventory, 1);
        remover.visitCurrency(new Currency(20));

        assertEquals(currencyInventory.getCurrentBalance().getAmount(), 0);


    }
    @Test
    public void testValidFalse(){
        CritterCard testCard = new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        CritterCard testCard2 = new CritterCard(1, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        Pack testPack = new Pack(0, "", "", new ArrayList<>(), new ArrayList<>());


        currencyInventory.setBalance(new Currency(100));

        packInventory.addPack(testPack);
        packInventory.addPack(testPack);
        packInventory.addPack(testPack);
        packInventory.addPack(testPack);

        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);

        cardInventory.addCard(testCard2);
        cardInventory.addCard(testCard2);

        TradeTransactionBuilder builder = new TradeTransactionBuilder();

        builder.addToGiven(new ItemStack<>(testPack, 1));
        builder.addToGiven(new ItemStack<>(new Currency(120)));

        builder.addToReceived(new ItemStack<>(new Currency(100)));

        TransactionHandler handler = new TransactionHandler(cardInventory, packInventory, currencyInventory);
        assertFalse(handler.verifyTransaction(builder.build()));

        builder.reset();

        builder.addToGiven(new ItemStack<>(testCard2, 100));
        builder.addToReceived(new ItemStack<IItem>(new Currency(100)));


        builder.reset();

        builder.addToGiven(new ItemStack<>(testPack, 100));
        builder.addToReceived(new ItemStack<IItem>(new Currency(100)));


        assertFalse(handler.verifyTransaction(builder.build()));
    }
    @Test
    public void testVerifyTrue(){
        CritterCard testCard = new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        CritterCard testCard2 = new CritterCard(1, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        Pack testPack = new Pack(0, "", "", new ArrayList<>(), new ArrayList<>());

        Pack awardPack = new Pack(1, "", "", new ArrayList<>(), new ArrayList<>());
        CritterCard awardCard = new CritterCard(2, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);

        currencyInventory.setBalance(new Currency(100));

        packInventory.addPack(testPack);
        packInventory.addPack(testPack);
        packInventory.addPack(testPack);
        packInventory.addPack(testPack);

        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);

        cardInventory.addCard(testCard2);
        cardInventory.addCard(testCard2);

        TradeTransactionBuilder builder = new TradeTransactionBuilder();
        builder.addToReceived(new ItemStack<>(awardCard, 2));
        builder.addToReceived(new ItemStack<>(awardPack, 1));
        builder.addToReceived(new ItemStack<>(new Currency(50)));

        builder.addToGiven(new ItemStack<>(testCard, 2));
        builder.addToGiven(new ItemStack<>(testPack, 1));
        builder.addToGiven(new ItemStack<>(new Currency(10)));

        TransactionHandler handler = new TransactionHandler(cardInventory, packInventory, currencyInventory);
        assertTrue(handler.verifyTransaction(builder.build()));

    }

    @Test
    public void TestTransaction(){
        CritterCard testCard = new CritterCard(0, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        CritterCard testCard2 = new CritterCard(1, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);
        Pack testPack = new Pack(0, "", "", new ArrayList<>(), new ArrayList<>());

        Pack awardPack = new Pack(1, "", "", new ArrayList<>(), new ArrayList<>());
        CritterCard awardCard = new CritterCard(2, " ", " ", 0, Card.Rarity.COMMON,0, 0, null);

        currencyInventory.setBalance(new Currency(100));

        packInventory.addPack(testPack);
        packInventory.addPack(testPack);
        packInventory.addPack(testPack);
        packInventory.addPack(testPack);

        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);
        cardInventory.addCard(testCard);

        cardInventory.addCard(testCard2);
        cardInventory.addCard(testCard2);

        TradeTransactionBuilder builder = new TradeTransactionBuilder();
        builder.addToReceived(new ItemStack<>(awardCard, 2));
        builder.addToReceived(new ItemStack<>(awardPack, 1));
        builder.addToReceived(new ItemStack<>(new Currency(50)));

        builder.addToGiven(new ItemStack<>(testCard, 2));
        builder.addToGiven(new ItemStack<>(testPack, 1));
        builder.addToGiven(new ItemStack<>(new Currency(10)));

        TransactionHandler handler = new TransactionHandler(cardInventory, packInventory, currencyInventory);
        handler.performTransaction(builder.build());

        assertEquals(currencyInventory.getCurrentBalance().getAmount(), 140);
        assertEquals(packInventory.getPackAmount(awardPack), 1);
        assertEquals(cardInventory.getCardAmount(awardCard), 2);

        assertEquals(packInventory.getPackAmount(testPack), 3);
        assertEquals(cardInventory.getCardAmount(testCard), 1);

    }
}
