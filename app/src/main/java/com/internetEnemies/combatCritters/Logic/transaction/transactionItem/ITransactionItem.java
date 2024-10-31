package com.internetEnemies.combatCritters.Logic.transaction.transactionItem;

import com.internetEnemies.combatCritters.Logic.transaction.IInventory;

/**
 * ITransactionItem.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    an item in a transaction
 */
public interface ITransactionItem {
    boolean verifyWith(IInventory inventory);
    void removeFrom(IInventory inventory);
    void addTo(IInventory inventory);
    
}
