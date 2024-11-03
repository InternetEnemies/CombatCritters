package com.internetEnemies.combatCritters.Logic.transaction;

/**
 * UnverifiedTransactionException.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    provide exception for attempting to perform an invalid transaction with verification
 */
public class UnverifiedTransactionException extends RuntimeException {
    public UnverifiedTransactionException(String message) {
        super(message);
    }
}
