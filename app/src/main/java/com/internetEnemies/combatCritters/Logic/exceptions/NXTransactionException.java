package com.internetEnemies.combatCritters.Logic.exceptions;

/**
 * NXTransactionException.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    exception for when I Transaction was expected but not found
 */
public class NXTransactionException extends RuntimeException {
    public NXTransactionException(String message) {
        super(message);
    }
}
