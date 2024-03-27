package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.battles.IRewardTransactionBuilder;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * RewardTransactionBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-27
 *
 * @PURPOSE:    Builder class for Reward Transaction objects.
 */

public class RewardTransactionBuilder implements IRewardTransactionBuilder {
    private int id;
    private List<ItemStack<?>> received;

    public RewardTransactionBuilder(){
        reset();;
    }
    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void addToReceived(ItemStack<?> item) {
        received.add(item);
    }

    @Override
    public RewardTransaction build() {
        return new RewardTransaction(id,received);
    }

    @Override
    public void reset() {
        received = new ArrayList<>();
    }

    @Override
    public void fromTransaction(RewardTransaction transaction) {
        setID(transaction.getId());
        for(ItemStack<?> stack : transaction.getReceived()) {
            addToReceived(stack);
        }
    }
}
