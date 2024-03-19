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
import com.internetEnemies.combatCritters.Logic.TradeUpHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpValidator;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CardSearchStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.objects.Card;

import org.junit.Before;
import org.junit.Test;

public class TradeUpHandlerTest {
    ITradeUpValidator tradeUpValidatorMock;
    ICardSearch cardSearchMock;
    ICardInventory cardInventoryMock;
    private ITradeUpHandler tradeUpHandler;
    @Before
    public void setup(){
        tradeUpValidatorMock = mock(TradeUpValidator.class);
        cardSearchMock = mock(CardSearchStub.class);
        cardInventoryMock = mock(CardInventoryStub.class);
        tradeUpHandler = new TradeUpHandler(tradeUpValidatorMock,cardSearchMock,cardInventoryMock);
    }

    @Test
    public void testGetCards(){
        tradeUpHandler.getCards(Card.Rarity.COMMON);
    }

    @Test
    public void testAddCard(){

    }

    @Test (expected = AssertionError.class)
    public void testAddCardNull(){

    }

    @Test
    public void testRemoveCard(){

    }

    @Test (expected = AssertionError.class)
    public void testRemoveCardNull(){

    }

    @Test (expected = AssertionError.class)
    public void testRemoveCardEmpty(){

    }

    @Test
    public void testGetSelectedCards(){

    }

    @Test
    public void testConfirmTradeUp(){

    }

    @Test
    public void testConfirmTradeUpFail(){

    }

    @Test (expected = AssertionError.class)
    public void testConfirmTradeUpNull(){

    }
}
