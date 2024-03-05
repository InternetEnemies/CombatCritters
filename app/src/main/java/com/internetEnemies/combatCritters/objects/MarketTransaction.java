package com.internetEnemies.combatCritters.objects;

import java.util.List;

/**
 * MarketTransaction.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-05
 *
 * @PURPOSE:    A specific transaction object used for items bought in the marketplace.
 */

public class MarketTransaction extends Transaction{
    private final Currency price;
    private double discount;

    public MarketTransaction(List<ItemStack<?>> received, Currency price){
        this(received, price, 0);
    }
    public MarketTransaction(List<ItemStack<?>> received, Currency price, double discount){
        super(received);
        this.price = price;
        this.discount = discount;
    }
    public Currency getPrice(){
        return new Currency((int)(price.getAmount() * discount));
    }
    public double getDiscount(){
        return discount;
    }
    public double getPercentageOff() {return discount*100;}
    public Currency getPriceWithoutDiscount(){
        return price;
    }
}
