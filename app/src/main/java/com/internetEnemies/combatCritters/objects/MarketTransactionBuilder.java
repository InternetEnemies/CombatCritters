package com.internetEnemies.combatCritters.objects;

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
public class MarketTransactionBuilder implements  IMarketTransactionBuilder{
    List<ItemStack<?>> received;
    private Currency price;
    private double discount;

    public MarketTransactionBuilder(){
        reset();
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
        return new MarketTransaction(received, price, discount);
    }

    @Override
    public void reset() {
        received = new ArrayList<>();
        price = new Currency(0);
        discount = 0;
    }
}
