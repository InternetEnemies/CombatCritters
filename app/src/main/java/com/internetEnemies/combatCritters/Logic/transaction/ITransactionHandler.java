package com.internetEnemies.combatCritters.Logic.transaction;

import com.internetEnemies.combatCritters.objects.Transaction;

/**
 * ITransactionHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    interface for handling transactions
 */
public interface ITransactionHandler {
    /**
     * verify a transaction can be performed.
     * ! Performing a transaction without verify may have unintended effects
     * @param transaction transaction to check
     * @return true if the transaction can be performed
     */
    boolean verify(Transaction<?,?> transaction);

    /**
     * perform the given transaction
     * @param transaction transaction to perform
     */
    void perform(Transaction<?,?> transaction);

    /**
     * verifies then performs a transaction
     * @param transaction transaction to perform
     * @throws UnverifiedTransactionException if the transaction cannot be verified
     */
    void verifiedPerform(Transaction<?,?> transaction);
}
