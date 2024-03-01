package com.internetEnemies.combatCritters.presentation;

/**
 * UIException.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    Exception thrown when the user(usually) is trying to do something that the ui is in
 *              bad state to do
 */
public class UIException extends Exception{
    public UIException(String message) {
        super(message);
    }
}
