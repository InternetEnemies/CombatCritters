/**
 * TradeUpHandlerTest.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      19-March-2024
 *
 * @PURPOSE:     Unit Test of TradeUpHandler using Mock
 */
package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.ITradeUpValidator;
import com.internetEnemies.combatCritters.Logic.TradeTransactionBuilder;
import com.internetEnemies.combatCritters.Logic.TradeUpHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpValidator;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CardSearchStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TradeUpHandlerTest {
    ITradeUpValidator tradeUpValidatorMock;
    ICardSearch cardSearchMock;
    ICardInventory cardInventoryMock;
    private ITradeUpHandler tradeUpHandler;
    @Before
    public void setup(){
        tradeUpValidatorMock = mock(ITradeUpValidator.class);
        cardSearchMock = mock(ICardSearch.class);
        cardInventoryMock = mock(ICardInventory.class);
        tradeUpHandler = new TradeUpHandler(tradeUpValidatorMock,cardSearchMock,cardInventoryMock, CardOrder.ID);
    }

    @Test
    public void testGetCards(){
        tradeUpHandler.getCards(Card.Rarity.COMMON);
        List<CardOrder> order = new ArrayList<>();
        List<Card.Rarity> rarities = new ArrayList<>();
        rarities.add(Card.Rarity.COMMON);
        order.add(CardOrder.NAME);
        CardFilter filter = new CardFilter(true,rarities,true,null,false);
        verify(cardSearchMock).get(order, filter);
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
        assert tempList.size() == 0;
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

    @Test
    public void testConfirmTradeUp(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(any(TradeTransaction.class));
        verify(cardInventoryMock).removeCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null),5);
        verify(cardInventoryMock).addCard(any(Card.class));
    }

    @Test
    public void testConfirmTradeUpFail(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(any(TradeTransaction.class));
        verifyNoInteractions(cardInventoryMock);
    }

    @Test
    public void testConfirmTradeUpEmpty(){
        assert tradeUpHandler.getSelectedCards().isEmpty();
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(any(TradeTransaction.class));
        verifyNoInteractions(cardInventoryMock);
    }
}
