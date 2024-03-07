package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Currency;

public class Bank implements IBank{
    private final ICurrencyInventory currencyInventory;

    public Bank(ICurrencyInventory currencyInventory) {
        this.currencyInventory = currencyInventory;
    }

    public Bank() {
        this.currencyInventory = new CurrencyInventoryStub();
    }

    public Currency getCurrentBalance(int id){
        return currencyInventory.getCurrentBalance(id);
    }
}
