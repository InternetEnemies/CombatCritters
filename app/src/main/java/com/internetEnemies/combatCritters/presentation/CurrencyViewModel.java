package com.internetEnemies.combatCritters.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.Logic.Bank;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.ArrayList;
import java.util.List;

public class CurrencyViewModel extends ViewModel {
    private final MutableLiveData<Currency> balance = new MutableLiveData<>();
    private final Bank bank;


    public CurrencyViewModel() {
        super();
        //TODO: get rid of this once the bank default constructor is added
        ICurrencyInventory currencyInventory = new CurrencyInventoryStub();
        bank = new Bank(currencyInventory);
        balance.setValue(bank.getCurrentBalance(1));
    }

    public void refreshBalance() {
        balance.setValue(bank.getCurrentBalance(1));
    }

    //TODO: remove this test method
    public void setBalance(int i) {
        balance.setValue(new Currency(balance.getValue().getAmount() - i));
    }


    public MutableLiveData<Currency> getBalance() {
        return balance;
    }
}
