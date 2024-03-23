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
import com.internetEnemies.combatCritters.Logic.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpHandler;
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

import java.util.ArrayList;
import java.util.List;

public class TradeUpHandlerTest {
    @Mock
    private ITradeUpValidator tradeUpValidatorMock;
    @Mock
    private ICardSearch cardSearchMock;
    @Mock
    private ITransactionHandler transactionHandlerMock;
    private ITradeUpHandler tradeUpHandler;

    @Before
    public void setup(){
        tradeUpValidatorMock = mock(ITradeUpValidator.class);
        cardSearchMock = mock(ICardSearch.class);
        transactionHandlerMock = mock(ITransactionHandler.class);
        List<CardOrder> cardOrder = new ArrayList<>();
        cardOrder.add(CardOrder.RARITY);
        cardOrder.add(CardOrder.NAME);
        tradeUpHandler = new TradeUpHandler(tradeUpValidatorMock,cardSearchMock,transactionHandlerMock,cardOrder);
    }

    @Test
    public void testGetCards(){
        ArgumentCaptor<CardFilter> cardFilterCaptor = ArgumentCaptor.forClass(CardFilter.class);
        tradeUpHandler.getCards(Card.Rarity.COMMON);
        verify(cardSearchMock).get(any(),cardFilterCaptor.capture());
        CardFilter cardFilterValue = cardFilterCaptor.getValue();
        assert cardFilterValue.getRarities().get(0) == Card.Rarity.COMMON;
        assert cardFilterValue.isOwned();
        assert cardFilterValue.isRarityWhitelist();
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
    public void testConfirmTradeUp(){
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
        verify(tradeUpValidatorMock).validate(captor.capture());
        verify(transactionHandlerMock).performTransaction(any(TradeTransaction.class));
        TradeTransaction transactionValues = captor.getValue();
        Card tempCard = (Card) transactionValues.getReceived().get(0).getItem();
        assert tempCard.getRarity() == Card.Rarity.UNCOMMON;
    }

    @Test
    public void testConfirmTradeUpFail(){
        List<ItemStack<Card>> uncommonList = new ArrayList<>();
        uncommonList.add(new ItemStack<>(new CritterCard(1,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        uncommonList.add(new ItemStack<>(new CritterCard(2,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        when(cardSearchMock.get(any(),any())).thenReturn(uncommonList);
        when(tradeUpValidatorMock.validate(any())).thenReturn(new TradeUpValidity(false,4));
        tradeUpHandler.addCard(new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null));
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(any(TradeTransaction.class));
        verifyNoInteractions(transactionHandlerMock);
    }

    @Test
    public void testConfirmTradeUpEmpty(){
        List<ItemStack<Card>> uncommonList = new ArrayList<>();
        uncommonList.add(new ItemStack<>(new CritterCard(1,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        uncommonList.add(new ItemStack<>(new CritterCard(2,"","",0,Card.Rarity.UNCOMMON,0,0,null),1));
        when(cardSearchMock.get(any(),any())).thenReturn(uncommonList);
        when(tradeUpValidatorMock.validate(any())).thenReturn(new TradeUpValidity(false,5));
        assert tradeUpHandler.getSelectedCards().isEmpty();
        tradeUpHandler.confirmTradeUp();
        verify(tradeUpValidatorMock).validate(any(TradeTransaction.class));
        verifyNoInteractions(transactionHandlerMock);
    }
}
