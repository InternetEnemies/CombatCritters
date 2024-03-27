package com.internetEnemies.combatCritters.Logic.battles.events.integerEvents;

/**
 * IIntegerListener.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/26/24
 *
 * @PURPOSE:    event listener for integers
 */
public interface IIntegerListener {
    /**
     * fired when the integer changes
     * @param i the new value of the integer
     */
    void onChange(int i);
}
