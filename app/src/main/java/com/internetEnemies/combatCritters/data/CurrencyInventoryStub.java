/**
 * CurrencyInventoryStub.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-29
 *
 * @PURPOSE:    implementation of ICurrencyInventory
 */

package com.internetEnemies.combatCritters.data;
import com.internetEnemies.combatCritters.objects.Currency;

public class CurrencyInventoryStub implements ICurrencyInventory{
    int balance;

    public CurrencyInventoryStub(){
        balance = 0;

    }

    @Override
    public Currency getCurrentBalance() {
        return new Currency(balance);
    }

    @Override
    public void addToBalance(Currency value) {
        setBalance(new Currency(balance + value.getAmount()));
    }

    @Override
    public void removeFromBalance(Currency value) {
        setBalance(new Currency(balance - value.getAmount()));
    }
    @Override
    public void setBalance(Currency value){
        assert(value.getAmount() >= 0);
        balance = value.getAmount();
    }
}
