/**
 * TradeUpHandlerIntegrationTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     22-March-2024
 *
 * PURPOSE:     Integration Tests for TradeUpHandler
 */

package com.internetEnemies.combatCritters;


import com.internetEnemies.combatCritters.Logic.tradeup.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.tradeup.ITradeUpValidator;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.tradeup.TradeUpHandler;
import com.internetEnemies.combatCritters.Logic.tradeup.TradeUpValidator;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandler;
import com.internetEnemies.combatCritters.Logic.exceptions.InvalidTradeUpCardsException;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardSearchHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemStack;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TradeUpHandlerIntegrationTest {
    @Mock
    private ITradeUpValidator tradeUpValidatorMock;
    @Mock
    private ICardSearch cardSearchMock;
    @Mock
    private ITransactionHandler transactionHandlerMock;
    private ICardInventory inventory;
    private ITradeUpHandler tradeUpHandler;

    private Card testCommonCard1;
    private Card testCommonCard2;
    private Card testCommonCard3;
    private Card testCommonCard4;
    private Card testCommonCard5;
    private Card testCommonCard6;
    @Before
    public void setup() throws IOException{
        String path = TestUtils.getDBPath();
        tradeUpValidatorMock = new TradeUpValidator();
        cardSearchMock = new CardSearchHSQLDB(path);
        transactionHandlerMock = new TransactionHandler(
                new CardInventoryHSQLDB(path),
                new PackInventoryHSQLDB(path),
                new CurrencyInventoryHSQLDB(path)
        );
        List<CardOrder> cardOrder = new ArrayList<>();
        cardOrder.add(CardOrder.RARITY);
        cardOrder.add(CardOrder.NAME);
        tradeUpHandler = new TradeUpHandler(tradeUpValidatorMock,cardSearchMock,transactionHandlerMock, cardOrder);
        RegistryCardHSQLDB cardReg = new RegistryCardHSQLDB(path);
        testCommonCard1 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard2 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard3 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard4 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard5 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard6 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.UNCOMMON,0,0,null));
        Card testCommonCard7 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.RARE,0,0,null));
        inventory = new CardInventoryHSQLDB(path);
        inventory.addCard(testCommonCard1);
        inventory.addCard(testCommonCard2);
        inventory.addCard(testCommonCard3);
        inventory.addCard(testCommonCard4);
        inventory.addCard(testCommonCard5);
        inventory.addCard(testCommonCard7);
    }

    @Test
    public void testGetCardsBlackList(){
        List<ItemStack<Card>> list = tradeUpHandler.getCards();
        int counter = 0;
        for(ItemStack<Card> itemStack: list){
            counter += itemStack.getAmount();
        }
        assert counter == 6;
    }

    @Test
    public void testGetCardsWhiteList(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        List<ItemStack<Card>> list = tradeUpHandler.getCards();
        int counter = 0;
        for(ItemStack<Card> itemStack: list){
            counter += itemStack.getAmount();
        }
        assert counter == 5;
    }

    @Test
    public void testGetCardsSubtractedList(){
        List<Card.Rarity> rarities = new ArrayList<>();
        rarities.add(Card.Rarity.COMMON);
        CardFilter filter = new CardFilter(true, rarities, true, null, false);
        List<CardOrder> cardOrder = new ArrayList<>();
        cardOrder.add(CardOrder.NAME);
        List<ItemStack<Card>> prevTempList = cardSearchMock.get(cardOrder,filter);
        int numBeforeSelect = itemCounter(prevTempList);
        tradeUpHandler.addCard(testCommonCard1);
        List<ItemStack<Card>> tempList = tradeUpHandler.getCards();
        tempList = tradeUpHandler.getCards();
        int numAfterSelect = itemCounter(tempList);
        assert numBeforeSelect == numAfterSelect+1;
    }

    @Test
    public void testGetSelectedCards(){
        List<Card> tempList = tradeUpHandler.getSelectedCards();
        assert tempList.isEmpty();
    }

    @Test
    public void testAddCard(){
        assert !tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null)).isValid();
        assert !tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null)).isValid();
        assert !tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null)).isValid();
        assert !tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null)).isValid();
        assert tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null)).isValid();
        List<Card> tempList = tradeUpHandler.getSelectedCards();
        assert tempList.size() == 5;
    }

    @Test (expected = AssertionError.class)
    public void testAddCardNull(){
        tradeUpHandler.addCard(null);
    }

    @Test
    public void testRemoveCard(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        List<Card> tempList = tradeUpHandler.getSelectedCards();
        assert tempList.size() == 1;
        assert !tradeUpHandler.removeCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null)).isValid();
        tempList = tradeUpHandler.getSelectedCards();
        assert tempList.isEmpty();
    }

    @Test (expected = AssertionError.class)
    public void testRemoveCardNull(){
        tradeUpHandler.removeCard(null);
    }

    @Test (expected = AssertionError.class)
    public void testRemoveCardEmpty(){
        assert tradeUpHandler.getSelectedCards().isEmpty();
        tradeUpHandler.removeCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
    }

    @Test (expected = AssertionError.class)
    public void testRemoveCardNotSelect(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.removeCard(new CritterCard(1,"","",0,Card.Rarity.COMMON,0,0,null));
    }

    @Test
    public void testConfirmTradeUp() throws InvalidTradeUpCardsException {
        List<CardOrder> cardOrder = new ArrayList<>();
        cardOrder.add(CardOrder.NAME);
        List<Card.Rarity> rarities = new ArrayList<>();
        rarities.add(Card.Rarity.UNCOMMON);
        List<ItemStack<Card>> inventoryUncommonList = cardSearchMock.get(cardOrder,new CardFilter(true,rarities,true,null,false));
        assert inventoryUncommonList.isEmpty();
        List<ItemStack<Card>> tempList = tradeUpHandler.getCards();
        int i = 0;
        for(ItemStack<Card> itemStack: tempList){
            if(itemStack.getItem().getRarity() == Card.Rarity.COMMON) {
                tradeUpHandler.addCard(itemStack.getItem());
                i++;
            }
            if(i == 5){
                break;
            }
        }
        assert tradeUpHandler.confirmTradeUp().getRarity() == Card.Rarity.UNCOMMON;
        inventoryUncommonList = cardSearchMock.get(cardOrder,new CardFilter(true,rarities,true,null,false));
        assert !inventoryUncommonList.isEmpty();
    }

    @Test (expected = InvalidTradeUpCardsException.class)
    public void testConfirmTradeUpFail() throws InvalidTradeUpCardsException {
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.confirmTradeUp();
    }

    @Test (expected = InvalidTradeUpCardsException.class)
    public void testConfirmTradeUpEmpty() throws InvalidTradeUpCardsException {
        assert tradeUpHandler.getSelectedCards().isEmpty();
        tradeUpHandler.confirmTradeUp();
    }

    @Test
    public void testGetCurrentRarity(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        assert tradeUpHandler.getCurrentTradeUpRarity() == Card.Rarity.UNCOMMON;
    }

    private int itemCounter(List<ItemStack<Card>> list){
        int counter = 0;
        for(ItemStack<Card> item: list){
            counter += item.getAmount();
        }
        return counter;
    }
}
