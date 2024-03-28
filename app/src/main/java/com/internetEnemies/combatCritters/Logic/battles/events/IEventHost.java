package com.internetEnemies.combatCritters.Logic.battles.events;

/**
 * IEventHost.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-25
 *
 * @PURPOSE:    provide an interface for event
 */
public interface IEventHost<T> {
    /**
     * subscribe to events from this host
     *
     * @param listener listener that will be called when an event is fired
     */
    void subscribe(EventListener<T> listener);

    /**
     * fires an event from the host
     *
     * @param payload payload of the event
     */
    void fireEvent(T payload);
}
