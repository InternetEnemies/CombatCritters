package com.internetEnemies.combatCritters.objects;

import java.util.List;
import java.util.Objects;

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
        return new Currency((int)(price.getAmount() - ( price.getAmount()*discount)));
    }
    public double getDiscount(){
        return discount;
    }
    public double getPercentageOff() {return discount*100;}
    public Currency getPriceWithoutDiscount(){
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketTransaction)) return false;
        if (!super.equals(o)) return false;
        MarketTransaction that = (MarketTransaction) o;
        return Double.compare(discount, that.discount) == 0 && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, discount);
    }
}
