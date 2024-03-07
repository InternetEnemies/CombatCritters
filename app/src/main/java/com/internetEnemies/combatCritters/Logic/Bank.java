/**
 * Bank.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-29
 *
 * @PURPOSE:    Storing the currency Inventory
 *              manage the currency of the user
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Currency;

public class Bank {
    private final ICurrencyInventory currencyInventory;

    public Bank(){
        this.currencyInventory = Database.getInstance().getCurrencyInventory();
    }

    public Bank(ICurrencyInventory currencyInventory) {
        this.currencyInventory = currencyInventory;
    }

    public Currency getCurrentBalance(){
        return currencyInventory.getCurrentBalance();
    }
}
