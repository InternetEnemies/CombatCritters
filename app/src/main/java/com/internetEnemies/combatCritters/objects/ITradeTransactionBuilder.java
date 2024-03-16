/**
 * ITradeTransactionBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    interface of Builder for Trade Transaction
 */

package com.internetEnemies.combatCritters.objects;

public interface ITradeTransactionBuilder {
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
     * Adds a generic to the list of items taken out as well as the amount of it.
     * @param item the generic being added.
     *
     */
    void addToGiven(ItemStack<?> item);

    /**
     * Builds the Transaction object
     * @return an instantiated Transaction object.
     *
     */
    Transaction build();
    /**
     * Resets the builder's parameters.
     *
     */
    void reset();

}
