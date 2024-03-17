package com.internetEnemies.combatCritters.objects;

/**
 * IMarketTransactionBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    Interface for MarketTransactionBuilder
 */

public interface IMarketTransactionBuilder {
    /**
     * Sets the id of the transaction
     * @param id the given ID
     */
    void setID(int id);
    /**
     * Set the price of the Transaction.
     * @param price
     */
    void setPrice(Currency price);
    /**
     * Add an item to the list of received Items.
     * @param item the ItemStack to be added.
     */
    void addToReceived(ItemStack<?> item);
    /**
     * Set the discount of the Price.
     * @param discount the discount of the transaction.
     */
    void setDiscount(double discount);
    /**
     * Creates new instance of a MarketTransaction.
     *
     */
    MarketTransaction build();

    /**
     * Resets the builder to default parameters.
     */
    void reset();
}
