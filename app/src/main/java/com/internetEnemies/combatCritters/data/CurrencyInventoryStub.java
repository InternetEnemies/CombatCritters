package com.internetEnemies.combatCritters.data;
import com.internetEnemies.combatCritters.objects.Currency;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyInventoryStub implements ICurrencyInventory{
    Map<Integer, Integer> currencyDB;

    public CurrencyInventoryStub(){
        currencyDB = new TreeMap<>();
        currencyDB.put(0, 0);
        currencyDB.put(1, 20);
    }

    @Override
    public Currency getCurrentBalance(int id) {
        return new Currency(currencyDB.get(id));
    }

    @Override
    public void addtoBalance(Currency value, int id) {
        currencyDB.put(id, currencyDB.get(id) + value.getAmount());
    }

    @Override
    public void removeFromBalance(Currency value, int id) {
        currencyDB.put(id, currencyDB.get(id) - value.getAmount());
    }
}
