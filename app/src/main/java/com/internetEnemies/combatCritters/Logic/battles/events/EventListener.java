package com.internetEnemies.combatCritters.Logic.battles.events;

/**
 * EventListener.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    provide generic event listening
 */
public interface EventListener <T>{
    /**
     * fired when an event occurs
     * @param payload payload of the event
     */
    void onEvent(T payload);
}
