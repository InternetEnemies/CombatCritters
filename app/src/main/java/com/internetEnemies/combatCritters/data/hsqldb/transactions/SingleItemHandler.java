package com.internetEnemies.combatCritters.data.hsqldb.transactions;

import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.SingleItem;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * SingleItemHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/2/24
 * 
 * @PURPOSE:    process the result of a TransactionItem db fetch into a single item
 */
public class SingleItemHandler implements ITransactionItemHandler<SingleItem> {
    @Override
    public SingleItem process(List<ItemStack<?>> items) {
        return new SingleItem(items.getFirst());
    }
}
