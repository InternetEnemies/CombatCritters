package com.internetEnemies.combatCritters.Logic.transaction.transactionItem;

import com.internetEnemies.combatCritters.Logic.transaction.IInventory;

/**
 * NullItem.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    represents null in a transaction. used to allow for one-sided transactions such as battle rewards or battle buy in
 */
public class NullItem implements ITransactionItem{
    @Override
    public boolean verifyWith(IInventory inventory) {
        return true; // true since we always have at least nothing
    }

    @Override
    public void removeFrom(IInventory inventory) {} // we cant remove nothing

    @Override
    public void addTo(IInventory inventory) {} // we cant add nothing
}
