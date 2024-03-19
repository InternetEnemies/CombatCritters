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

import org.junit.Before;

public class TradeUpValidatorTest {
    @Before
    public void setup(){
        ITradeUpValidator ITradeUpValidatorMock = mock(ITradeUpValidator.class);
    }
}
