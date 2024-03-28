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
     * Sets the image of the transaction
     * @param image the given image name
     */
    void setImage(String image);
    /**
     * Sets the name of the Trader
     * @param name the given name
     */
    void setName(String name);



    /**
     * Builds the Transaction object
     *
     * @return an instantiated Transaction object.
     */
    TradeTransaction build();
    /**
     * Resets the builder's parameters.
     *
     */
    void reset();

    /**
     * build the transaction from a transaction
     * @param transaction transaction to build from
     */
    void fromTransaction(TradeTransaction transaction);
}
