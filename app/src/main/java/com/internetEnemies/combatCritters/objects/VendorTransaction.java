package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.MultiItem;
import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.SingleItem;

import java.util.List;

/**
 * VendorTransaction.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    represents a transaction with a vendor
 */
public class VendorTransaction extends Transaction<MultiItem, SingleItem> {
    public VendorTransaction(int id, List<ItemStack<?>> give, ItemStack<?> receive) {
        super(id, new MultiItem(give), new SingleItem(receive));
    }
}
