package com.internetEnemies.combatCritters.data;
import com.internetEnemies.combatCritters.objects.Currency;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyInventoryStub implements ICurrencyInventory{
    Map<Integer, Integer> currencyDB;

    public CurrencyInventoryStub(){
        currencyDB = new TreeMap<>();
        currencyDB.put(0, 30);
        currencyDB.put(1, 20);
    }

    @Override
    public Currency getCurrentBalance(int id) {
        assert (currencyDB.get(id) != null);
        return new Currency(currencyDB.get(id));
    }

    @Override
    public void addToBalance(Currency value, int id) {
        assert (currencyDB.get(id) != null);
        setBalance(new Currency(currencyDB.get(id) + value.getAmount()), id);
    }

    @Override
    public void removeFromBalance(Currency value, int id) {
        assert (currencyDB.get(id) != null);
        setBalance(new Currency(currencyDB.get(id) - value.getAmount()), id);
    }

    public void setBalance(Currency value, int id){
        assert(value.getAmount() > 0);
        currencyDB.put(id, value.getAmount());
    }
}
