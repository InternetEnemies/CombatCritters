/**
 * TradeUpValidatorTest.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      19-March-2024
 *
 * @PURPOSE:     Unit Test of TradeUpValidator using Mock
 */
package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.ITradeUpValidator;
import com.internetEnemies.combatCritters.Logic.TradeUpValidator;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.TradeUpValidity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

public class TradeUpValidatorTest {
    private ITradeUpValidator tradeUpValidator;
    private TradeTransaction tradeTransactionMock;
    private ItemStack<?> customItemStack;
    private ItemStack<?> customItemStack2;
    private final CritterCard commonCard = new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null);
    private final CritterCard uncommonCard = new CritterCard(0,"","",0,Card.Rarity.COMMON,0,0,null);
    @Before
    public void setup(){
        tradeUpValidator= new TradeUpValidator();
        tradeTransactionMock = mock(TradeTransaction.class);
    }

    @Test
    public void testValidate(){
        customItemStack = new ItemStack<>(commonCard,5);
        List<ItemStack<?>> tempList = new ArrayList<>();
        tempList.add(customItemStack);
        when(tradeTransactionMock.getReceived()).thenReturn(tempList);
        tempList = new ArrayList<>();
        customItemStack = new ItemStack<>(uncommonCard,1);
        tempList.add(customItemStack);
        when(tradeTransactionMock.getGiven()).thenReturn(tempList);

        TradeUpValidity tempValidity = tradeUpValidator.validate(tradeTransactionMock);
        assert tempValidity.isValid();
        verify(tradeTransactionMock).getGiven();
        verify(tradeTransactionMock).getReceived();
    }

    @Test (expected = AssertionError.class)
    public void testValidateDifferentRarity(){
        customItemStack = new ItemStack<>(commonCard,4);
        customItemStack2 = new ItemStack<>(uncommonCard,1);
        List<ItemStack<?>> tempList = new ArrayList<>();
        tempList.add(customItemStack);
        tempList.add(customItemStack2);
        when(tradeTransactionMock.getReceived()).thenReturn(tempList);
        tempList = new ArrayList<>();
        customItemStack = new ItemStack<>(uncommonCard,1);
        tempList.add(customItemStack);
        when(tradeTransactionMock.getGiven()).thenReturn(tempList);
        tradeUpValidator.validate(tradeTransactionMock);
    }

    @Test
    public void testValidateCardOverwhelmed(){
        customItemStack = new ItemStack<>(commonCard,6);
        List<ItemStack<?>> tempList = new ArrayList<>();
        tempList.add(customItemStack);
        when(tradeTransactionMock.getReceived()).thenReturn(tempList);
        tempList = new ArrayList<>();
        customItemStack = new ItemStack<>(uncommonCard,1);
        tempList.add(customItemStack);
        when(tradeTransactionMock.getGiven()).thenReturn(tempList);

        TradeUpValidity tempValidity = tradeUpValidator.validate(tradeTransactionMock);
        assert !tempValidity.isValid();
        assert tempValidity.getDifference() == -1;
    }

    @Test
    public void testValidateNotEnoughCards(){

    }
}
