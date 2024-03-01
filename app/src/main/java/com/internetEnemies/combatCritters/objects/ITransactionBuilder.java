package com.internetEnemies.combatCritters.objects;

public interface ITransactionBuilder<T> {

    /**
     * Adds a generic to the list of received items as well as the amount of it.
     * @param item the generic being added.
     *
     */
    void addToReceived(TransactionItem<T> item);
    /**
     * Adds a generic to the list of items taken out as well as the amount of it.
     * @param item the generic being added.
     *
     */
    void addToGiven(TransactionItem<T> item);
    /**
     * Builds the Transaction object
     * @return an instantiated Transaction object.
     *
     */
    Transaction<T> build();
    /**
     * Resets the builder's parameters.
     *
     */
    void reset();

}
