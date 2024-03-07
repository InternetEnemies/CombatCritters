package com.internetEnemies.combatCritters.data;
import com.internetEnemies.combatCritters.objects.Currency;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyInventoryStub implements ICurrencyInventory{
    int balance;

    public CurrencyInventoryStub(){balance = 100;}

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
