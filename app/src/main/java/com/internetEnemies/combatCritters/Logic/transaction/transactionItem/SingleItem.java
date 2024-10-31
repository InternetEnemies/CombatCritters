package com.internetEnemies.combatCritters.Logic.transaction.transactionItem;

import com.internetEnemies.combatCritters.Logic.transaction.IInventory;
import com.internetEnemies.combatCritters.objects.ItemStack;

/**
 * SingleItem.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    represents a single item in a transaction
 */
public class SingleItem implements ITransactionItem{
    ItemStack<?> item;
    public SingleItem(ItemStack<?> item) {
        this.item = item;
    }
    @Override
    public boolean verifyWith(IInventory inventory) {
        return inventory.has(this.item);
    }

    @Override
    public void removeFrom(IInventory inventory) {
        inventory.remove(this.item);
    }

    @Override
    public void addTo(IInventory inventory) {
        inventory.add(this.item);
    }
}
