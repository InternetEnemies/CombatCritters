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

import org.junit.Before;
import org.junit.Test;

public class TradeUpHandlerTest {
    @Before
    public void setup(){
        ITradeUpHandler ITradeUpHandlerMock = mock(ITradeUpHandler.class);
    }

    @Test
    public void testGetCards(){

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
    public void testConfirmTradeUp(){

    }

    @Test
    public void testConfirmTradeUpFail(){

    }

    @Test (expected = AssertionError.class)
    public void testConfirmTradeUpNull(){

    }
}
