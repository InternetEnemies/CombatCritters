package com.internetEnemies.combatCritters.objects.battles;

import com.internetEnemies.combatCritters.objects.ItemStack;

/**
 * IRewardTransactionBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-27
 *
 * @PURPOSE:    interface of Builder for Reward Transaction
 */

public interface IRewardTransactionBuilder {
    /**
     * Sets the id of the transaction
     * @param id the given ID
     */
    void setID(int id);

    /**
     * Adds a generic to the list of received items as well as the amount of it.
     * @param item the generic being added.
     *
     */
    void addToReceived(ItemStack<?> item);

    /**
     * Builds the Transaction object
     *
     * @return an instantiated Transaction object.
     */
    RewardTransaction build();

    /**
     * Resets the builder's parameters.
     *
     */
    void reset();
}
