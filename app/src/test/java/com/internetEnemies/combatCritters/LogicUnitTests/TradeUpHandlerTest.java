/**
 * TradeUpHandlerTest.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      19-March-2024
 *
 * @PURPOSE:     Unit Test of TradeUpHandler using Mock
 */
package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.ITradeUpValidator;
import com.internetEnemies.combatCritters.Logic.ITransactionHandler;
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
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class TradeUpHandlerTest {
    @Mock
    private ITradeUpValidator tradeUpValidatorMock;
    @Mock
    private ICardSearch cardSearchMock;
    @Mock
    private ICardInventory cardInventoryMock;
    @Mock
    private ITransactionHandler transactionHandlerMock;
    private ITradeUpHandler tradeUpHandler;

    @Before
    public void setup(){
        tradeUpValidatorMock = mock(ITradeUpValidator.class);
        cardSearchMock = mock(ICardSearch.class);
        cardInventoryMock = mock(ICardInventory.class);
        transactionHandlerMock = mock(ITransactionHandler.class);
        tradeUpHandler = new TradeUpHandler(tradeUpValidatorMock,cardSearchMock,cardInventoryMock,transactionHandlerMock,CardOrder.ID);
    }

    @Test
    public void testGetCards(){
        ArgumentCaptor<CardFilter> cardFilterCaptor = ArgumentCaptor.forClass(CardFilter.class);
        tradeUpHandler.getCards(Card.Rarity.COMMON);
        List<CardOrder> order = new ArrayList<>();
        List<Card.Rarity> rarities = new ArrayList<>();
        rarities.add(Card.Rarity.COMMON);
        order.add(CardOrder.NAME);
        verify(cardSearchMock).get(any(List.class),cardFilterCaptor.capture());
        CardFilter cardFilterValue = cardFilterCaptor.getValue();
        assert cardFilterValue.getRarities().get(0) == Card.Rarity.COMMON;
        assert cardFilterValue.isOwned();
        assert cardFilterValue.isRarityWhitelist();
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

    @Test
    public void testConfirmTradeUp(){
        ArgumentCaptor<TradeTransaction> captor = ArgumentCaptor.forClass(TradeTransaction.class);
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(captor.capture());
        verify(transactionHandlerMock).performTransaction(any(TradeTransaction.class));
        TradeTransaction transactionValues = captor.getValue();
        Card tempCard = (Card) transactionValues.getGiven().get(0).getItem();
        assert tempCard.getRarity() == Card.Rarity.UNCOMMON;
    }

    @Test
    public void testConfirmTradeUpFail(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(any(TradeTransaction.class));
        verifyNoInteractions(transactionHandlerMock);
    }

    @Test
    public void testConfirmTradeUpEmpty(){
        assert tradeUpHandler.getSelectedCards().isEmpty();
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(any(TradeTransaction.class));
        verifyNoInteractions(transactionHandlerMock);
    }
}
