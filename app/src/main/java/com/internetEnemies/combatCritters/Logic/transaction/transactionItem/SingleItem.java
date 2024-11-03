package com.internetEnemies.combatCritters.Logic.transaction.transactionItem;

import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;
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
    public boolean verifyWith(IParticipant inventory) {
        return inventory.has(this.item);
    }

    @Override
    public void removeFrom(IParticipant inventory) {
        inventory.remove(this.item);
    }

    @Override
    public void addTo(IParticipant inventory) {
        inventory.add(this.item);
    }
    
    public ItemStack<?> getItem() {
        return item;
    }
}
