package com.internetEnemies.combatCritters.Logic.exceptions;

/**
 * NoSuchUserException.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/24/24
 * 
 * @PURPOSE:    exception for when a user is not found
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
