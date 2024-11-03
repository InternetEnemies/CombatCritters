package com.internetEnemies.combatCritters.data.hsqldb.transactions;

import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.MultiItem;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * MultiItemHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/2/24
 * 
 * @PURPOSE:    Process the result of a transaction item fetch into a MultiItem
 */
public class MultiItemHandler implements ITransactionItemHandler<MultiItem> {
    @Override
    public MultiItem process(List<ItemStack<?>> items) {
        return new MultiItem(items);
    }
}
