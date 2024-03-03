package com.internetEnemies.combatCritters.objects;

public interface ITransactionBuilder {

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
     * Sets the type of transaction.
     * @param transaction type to be set.
     */
    void setTransactionType(Transaction.TransactionType type);
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
