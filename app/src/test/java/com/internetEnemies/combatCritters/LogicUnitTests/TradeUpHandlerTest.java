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

import com.internetEnemies.combatCritters.Logic.tradeup.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.tradeup.ITradeUpValidator;
import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.Logic.tradeup.TradeUpHandler;
import com.internetEnemies.combatCritters.Logic.exceptions.InvalidTradeUpCardsException;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TradeUpHandlerTest {
    @Mock
    private ITradeUpValidator tradeUpValidatorMock;
    @Mock
    private ICardSearch cardSearchMock;
    @Mock
    private ITransactionHandlerDeprecated transactionHandlerMock;
    private ITradeUpHandler tradeUpHandler;

    @Before
    public void setup(){
        tradeUpValidatorMock = mock(ITradeUpValidator.class);
        cardSearchMock = mock(ICardSearch.class);
        transactionHandlerMock = mock(ITransactionHandlerDeprecated.class);
        List<CardOrder> cardOrder = new ArrayList<>();
        cardOrder.add(CardOrder.RARITY);
        cardOrder.add(CardOrder.NAME);
        tradeUpHandler = new TradeUpHandler(tradeUpValidatorMock,cardSearchMock,transactionHandlerMock,cardOrder);
    }

    @Test
    public void testGetCardsBlackList(){
        ArgumentCaptor<CardFilter> cardFilterCaptor = ArgumentCaptor.forClass(CardFilter.class);
        tradeUpHandler.getCards();
        verify(cardSearchMock).get(any(),cardFilterCaptor.capture());
        CardFilter cardFilterValue = cardFilterCaptor.getValue();
        assert cardFilterValue.getRarities().get(0) == Card.Rarity.LEGENDARY;
        assert cardFilterValue.isOwned();
        assert !cardFilterValue.isRarityWhitelist();
    }

    @Test
    public void testGetCardsWhitelist(){
        ArgumentCaptor<CardFilter> cardFilterCaptor = ArgumentCaptor.forClass(CardFilter.class);
        CardFilter cardFilterValue;
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        List<ItemStack<Card>> tempList = tradeUpHandler.getCards();
        verify(cardSearchMock).get(any(),cardFilterCaptor.capture());
        cardFilterValue = cardFilterCaptor.getValue();
        assert cardFilterValue.getRarities().get(0) == Card.Rarity.COMMON;
        assert cardFilterValue.isOwned();
        assert cardFilterValue.isRarityWhitelist();
    }

    @Test
    public void testGetCardsEmptyReturn(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        assert tradeUpHandler.getCards().isEmpty();
    }

    @Test
    public void testGetSelectedCards(){
        List<Card> tempList = tradeUpHandler.getSelectedCards();
        assert tempList.isEmpty();
    }

    @Test
    public void testAddCard(){
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
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
    public void testConfirmTradeUp() throws InvalidTradeUpCardsException {
        List<ItemStack<Card>> uncommonList = new ArrayList<>();
        uncommonList.add(new ItemStack<>(new CritterCard(1,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        uncommonList.add(new ItemStack<>(new CritterCard(2,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        when(cardSearchMock.get(any(),any())).thenReturn(uncommonList);
        when(tradeUpValidatorMock.validate(any())).thenReturn(new TradeUpValidity(true,0));
        when(transactionHandlerMock.performTransaction(any(TradeTransaction.class))).thenReturn(true);
        ArgumentCaptor<TradeTransaction> captor = ArgumentCaptor.forClass(TradeTransaction.class);
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock, Mockito.times(6)).validate(any());
        verify(transactionHandlerMock).performTransaction(captor.capture());
        TradeTransaction transactionValues = captor.getValue();
        Card tempCard = (Card) transactionValues.getReceived().get(0).getItem();
        assert tempCard.getRarity() == Card.Rarity.UNCOMMON;
    }

    @Test (expected = InvalidTradeUpCardsException.class)
    public void testConfirmTradeUpFail() throws InvalidTradeUpCardsException {
        List<ItemStack<Card>> uncommonList = new ArrayList<>();
        uncommonList.add(new ItemStack<>(new CritterCard(1,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        uncommonList.add(new ItemStack<>(new CritterCard(2,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        when(cardSearchMock.get(any(),any())).thenReturn(uncommonList);
        when(tradeUpValidatorMock.validate(any())).thenReturn(new TradeUpValidity(false,4));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock, Mockito.times(2)).validate(any());
        verifyNoInteractions(transactionHandlerMock);
    }

    @Test (expected = InvalidTradeUpCardsException.class)
    public void testConfirmTradeUpEmpty() throws InvalidTradeUpCardsException {
        List<ItemStack<Card>> uncommonList = new ArrayList<>();
        uncommonList.add(new ItemStack<>(new CritterCard(1,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        uncommonList.add(new ItemStack<>(new CritterCard(2,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        when(cardSearchMock.get(any(),any())).thenReturn(uncommonList);
        when(tradeUpValidatorMock.validate(any())).thenReturn(new TradeUpValidity(false,5));
        assert tradeUpHandler.getSelectedCards().isEmpty();
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(any());
        verifyNoInteractions(transactionHandlerMock);
    }

    @Test
    public void testGetCurrentRarity(){
        List<ItemStack<Card>> uncommonList = new ArrayList<>();
        uncommonList.add(new ItemStack<>(new CritterCard(1,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        uncommonList.add(new ItemStack<>(new CritterCard(2,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        when(cardSearchMock.get(any(),any())).thenReturn(uncommonList);
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        assert tradeUpHandler.getCurrentTradeUpRarity() == Card.Rarity.UNCOMMON;
    }
}
