package com.internetEnemies.combatCritters.data.exception;

/**
 * NXDeckException.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/7/24
 * 
 * @PURPOSE:    error for non existent decks
 */
public class NXDeckException extends RuntimeException {
    public NXDeckException(String message) {
        super(message);
    }
}
