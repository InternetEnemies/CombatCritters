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

public class TradeUpHandlerTest {
    @Before
    public void setup(){
        ITradeUpHandler ITradeUpHandlerMock = mock(ITradeUpHandler.class);
    }
}
