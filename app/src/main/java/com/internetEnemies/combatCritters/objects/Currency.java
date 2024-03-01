package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;

public class Currency implements IVisitableItem{
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

    public void accept(IItemVisitor visitor){
        visitor.visitCurrency(this);
    }

}
