package com.internetEnemies.combatCritters.objects.battles;

import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;

/**
 * RewardTransaction.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-27
 *
 * @PURPOSE:    A specific transaction object used for battle opponent reward.
 */

public class RewardTransaction extends Transaction {
    public RewardTransaction(int id, List<ItemStack<?>> received) {
        super(id, received);
    }
}
