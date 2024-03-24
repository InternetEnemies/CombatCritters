package com.internetEnemies.combatCritters.Logic.battles.stateHandlers.listeners;

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
