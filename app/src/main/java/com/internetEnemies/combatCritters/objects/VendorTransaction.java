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
    public static VendorTransaction of(int id, List<ItemStack<?>> tx, ItemStack<?> rx) {
        return new VendorTransaction(id, new MultiItem(tx), new SingleItem(rx));
    }
    public VendorTransaction(int id, MultiItem multiItem, SingleItem singleItem) {
        super(id, multiItem, singleItem);
    }
}
