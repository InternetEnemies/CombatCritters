package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Currency;

public class Bank implements IBank{
    private final ICurrencyInventory currencyInventory;

    public Bank(ICurrencyInventory currencyInventory) {
        this.currencyInventory = currencyInventory;
    }

    //TODO dont know if this is the right way to do this
    public Bank() {
        this.currencyInventory = Database.getInstance().getCurrencyInventory();
    }

    public Currency getCurrentBalance(){
        return currencyInventory.getCurrentBalance();
    }
}
