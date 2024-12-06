package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.MultiItem;
import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.NullItem;

import java.util.List;

/**
 * RewardTransaction.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/5/24
 * 
 * @PURPOSE:    reward transaction data holder
 */
public class RewardTransaction extends Transaction<MultiItem, NullItem>{
    public RewardTransaction(List<ItemStack<?>> rewards) {
        this(-1, new NullItem(), new MultiItem(rewards));
    }
    public RewardTransaction(int id, NullItem nullItem, MultiItem multiItem) {
        super(id, multiItem, nullItem);
    }
}
