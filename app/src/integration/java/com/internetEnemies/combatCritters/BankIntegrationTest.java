package com.internetEnemies.combatCritters;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.Logic.inventory.Bank;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.objects.Currency;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * BankIntegrationTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    integration tests for Bank
 */
public class BankIntegrationTest {

    Bank bank;
    ICurrencyInventory currencyInventory;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        currencyInventory = new CurrencyInventoryHSQLDB(path);
        currencyInventory.setBalance(new Currency(0));
        bank = new Bank(currencyInventory);
    }

    @Test
    @Ignore
    public void testGetBalance(){

        Currency testReturn0 = bank.getCurrentBalance();
        assertEquals(testReturn0.getAmount(), 0);
    }
}
