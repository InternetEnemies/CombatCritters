package com.internetEnemies.combatCritters.Logic.battles.events.integerEvents;

/**
 * IIntegerEventHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/26/24
 *
 * @PURPOSE:    Event handler for integers (I really dont like null checking an int)
 */
public interface IIntegerEventHandler {
    /**
     * add a new listener to this event
     *
     * @param listener event listener for the integer
     */
    void subscribe(IIntegerListener listener);

    /**
     * fires on change events to every listener
     *
     * @param i new value of the int
     */
    void fireEvent(int i);
}
