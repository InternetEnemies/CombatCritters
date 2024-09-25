package com.internetEnemies.combatCritters.Logic.exceptions;

/**
 * UsernameConflictException.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/24/24
 * 
 * @PURPOSE:    thrown if a user with a username already exists
 */
public class UsernameConflictException extends RuntimeException {
    public UsernameConflictException(String message) {
        super(message);
    }
}
