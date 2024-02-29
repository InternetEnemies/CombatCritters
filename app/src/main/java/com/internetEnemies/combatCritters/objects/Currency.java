package com.internetEnemies.combatCritters.objects;

public class Currency {
    private int amount;

    public Currency(){
        amount = 0;
    }
    public Currency(int amount){
        this.amount = amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return amount;
    }
}
