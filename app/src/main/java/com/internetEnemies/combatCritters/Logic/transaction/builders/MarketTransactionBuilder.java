package com.internetEnemies.combatCritters.Logic.transaction.builders;

import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IMarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * MarketTransactionBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    Builder object for MarketTransaction
 */
public class MarketTransactionBuilder implements IMarketTransactionBuilder {

    private int id;
    private List<ItemStack<?>> received;
    private Currency price;
    private double discount;

    public MarketTransactionBuilder(){
        reset();
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void setPrice(Currency price) {
        this.price = price;
    }

    @Override
    public void addToReceived(ItemStack<?> item) {
        received.add(item);
    }

    @Override
    public void setDiscount(double discount){
        this.discount = discount;
    }

    @Override
    public MarketTransaction build() {
        return new MarketTransaction(id, received, price, discount);
    }

    @Override
    public void reset() {
        received = new ArrayList<>();
        price = new Currency(0);
        discount = 0;
    }
}
