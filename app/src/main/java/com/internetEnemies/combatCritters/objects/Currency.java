package com.internetEnemies.combatCritters.objects;
import com.internetEnemies.combatCritters.Logic.IItemVisitor;

/**
 * Currency.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    Currency object that passes a value as a DSO.
 */

public class Currency implements IItem {
    private int amount;

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
