/**
 * TradeUpHandlerIntegrationTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     22-March-2024
 *
 * PURPOSE:     Integration Tests for TradeUpHandler
 */

package com.internetEnemies.combatCritters.IntegrationTests;


import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.ITradeUpValidator;
import com.internetEnemies.combatCritters.Logic.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpValidator;
import com.internetEnemies.combatCritters.Logic.TransactionHandler;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardSearchHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemStack;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;

public class TradeUpHandlerIntegrationTest {
    @Mock
    private ITradeUpValidator tradeUpValidatorMock;
    @Mock
    private ICardSearch cardSearchMock;
    @Mock
    private ITransactionHandler transactionHandlerMock;
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
        tradeUpHandler = new TradeUpHandler(tradeUpValidatorMock,cardSearchMock,transactionHandlerMock, CardOrder.ID);
        RegistryCardHSQLDB cardReg = new RegistryCardHSQLDB(path);
        testCommonCard1 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard2 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard3 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard4 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard5 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        testCommonCard6 = cardReg.addCard(new CritterCard(0,"","",0,Card.Rarity.UNCOMMON,0,0,null));
        ICardInventory inventory = new CardInventoryHSQLDB(path);
        inventory.addCard(testCommonCard1);
        inventory.addCard(testCommonCard2);
        inventory.addCard(testCommonCard3);
        inventory.addCard(testCommonCard4);
        inventory.addCard(testCommonCard5);
    }

    @Test
    public void testGetCards(){
        List<ItemStack<Card>> list = tradeUpHandler.getCards(Card.Rarity.COMMON);
        for(ItemStack<Card> itemStack: list){
            assert itemStack.getItem().getRarity() == Card.Rarity.COMMON;
        }

    }

    @Test
    public void testGetSelectedCards(){
        List<ItemStack<Card>> tempList = tradeUpHandler.getSelectedCards();
        assert tempList.isEmpty();
    }

    @Test
    public void testAddCard(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        List<ItemStack<Card>> tempList = tradeUpHandler.getSelectedCards();
        assert tempList.size() == 1;
        assert tempList.get(0).getAmount() == 5;
    }

    @Test (expected = AssertionError.class)
    public void testAddCardNull(){
        tradeUpHandler.addCard(null);
    }

    @Test (expected = AssertionError.class)
    public void testAddCardDifferentRarity(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.RARE,0,0,null));
    }

    @Test
    public void testRemoveCard(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        List<ItemStack<Card>> tempList = tradeUpHandler.getSelectedCards();
        assert tempList.size() == 1;
        tradeUpHandler.removeCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
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
    public void testConfirmTradeUp(){
        assert tradeUpHandler.getCards(Card.Rarity.UNCOMMON).isEmpty();
        List<ItemStack<Card>> tempList = tradeUpHandler.getCards(Card.Rarity.COMMON);
        int i = 0;
        for(ItemStack<Card> itemStack: tempList){
            tradeUpHandler.addCard(itemStack.getItem());
            i++;
            if(i == 5){
                break;
            }
        }
        assert tradeUpHandler.confirmTradeUp().isValid();
        assert !tradeUpHandler.getCards(Card.Rarity.UNCOMMON).isEmpty();
    }

    @Test
    public void testConfirmTradeUpFail(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        assert !tradeUpHandler.confirmTradeUp().isValid();
    }

    @Test
    public void testConfirmTradeUpEmpty(){
        assert tradeUpHandler.getSelectedCards().isEmpty();
        assert !tradeUpHandler.confirmTradeUp().isValid();
    }
}
