package com.internetEnemies.combatCritters.objects;
import com.internetEnemies.combatCritters.Logic.IItemVisitor;

import java.util.Objects;

/**
 * Currency.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    Currency object that passes a value as a DSO.
 */

public class Currency implements IItem {
    private final int amount;

    public Currency(int amount){
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    public void accept(IItemVisitor visitor){
        visitor.visitCurrency(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency currency = (Currency) o;
        return amount == currency.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
