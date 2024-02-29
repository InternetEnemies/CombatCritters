package com.internetEnemies.combatCritters.presentation;

/**
 * exception that will likely be thrown because the user is doing something that cant be done
 */
public class UIException extends Exception{
    public UIException(String message) {
        super(message);
    }
}
