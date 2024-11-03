package com.internetEnemies.combatCritters.data.hsqldb.transactions;

import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.ITransactionItem;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * ITransactionItemHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/2/24
 * 
 * @PURPOSE:    interface for processing transaction item db records
 */
public interface ITransactionItemHandler<T extends ITransactionItem> {
    T process(List<ItemStack<?>> items);
}
