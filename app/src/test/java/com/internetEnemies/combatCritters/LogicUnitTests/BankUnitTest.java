package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.inventory.Bank;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Currency;

import org.junit.Before;
import org.junit.Test;

public class BankUnitTest {

    Bank bank;
    ICurrencyInventory currencyInventory;

    @Before
    public void setup(){
        currencyInventory = new CurrencyInventoryStub();
        currencyInventory.setBalance(new Currency(0));
        bank = new Bank(currencyInventory);
    }

    @Test
    public void testGetBalance(){

        Currency testReturn0 = bank.getCurrentBalance();
        assertEquals(testReturn0.getAmount(), 0);
    }
    
    @Test
    public void test_fail() throws Exception{
        throw new Exception("Fail");
    }
}
