package com.internetEnemies.combatCritters.objects;

import java.util.List;

public class MarketTransaction extends Transaction{
    private Currency price;
    private double discount;

    public MarketTransaction(List<ItemStack<?>> recieved, Currency price){
        this.received = recieved;
        this.price = price;
        this.discount = 0;
    }
    public MarketTransaction(List<ItemStack<?>> given, Currency price, double discount){
        this.received = given;
        this.price = price;
        this.discount = discount;
    }
    public Currency getPrice(){
        return price;
    }
    public double getDiscount(){
        return discount;
    }
    public Currency getPriceWithDiscount(){
        return new Currency((int)(price.getAmount() * discount), price.getId());
    }
}
