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
import com.internetEnemies.combatCritters.objects.TradeTransaction;

import org.junit.Before;
import org.junit.Test;

public class TradeUpValidatorTest {
    private ITradeUpValidator tradeUpValidator;
    private TradeTransaction tradeTransactionMock;
    @Before
    public void setup(){
        tradeUpValidator= new TradeUpValidator();
        tradeTransactionMock = mock(TradeTransaction.class);
    }

    @Test
    public void testValidate(){

    }

    @Test (expected = AssertionError.class)
    public void testValidateDifferentRarity(){

    }

    @Test
    public void testValidateCardOverwhelmed(){

    }

    @Test
    public void testValidateNotEnoughCards(){

    }
}
