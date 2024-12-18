package com.internetEnemies.combatCritters.Logic.transaction.transactionItem;

import com.internetEnemies.combatCritters.Logic.transaction.participant.IParticipant;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiItem.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    represents multiple items in a transaction
 */
public class MultiItem implements ITransactionItem {
    private final List<SingleItem> items;

    public MultiItem(List<ItemStack<?>> items){
        this.items = items.stream().map(SingleItem::new).toList();
    }
    @Override
    public boolean verifyWith(IParticipant inventory) {
        boolean result = true;
        for (SingleItem item : items) {
            if(!item.verifyWith(inventory)){
                result = false;
            }
        }
        return result;
    }

    @Override
    public void removeFrom(IParticipant inventory) {
        for (SingleItem item : items) {
            item.removeFrom(inventory);
        }
    }

    @Override
    public void addTo(IParticipant inventory) {
        for (SingleItem item : items) {
            item.addTo(inventory);
        }
    }
    
    public List<ItemStack<?>> getItems() {
        List<ItemStack<?>> result = new ArrayList<>();
        for (SingleItem item : items) {
            result.add(item.getItem());
        }
        return result;
    }
}
