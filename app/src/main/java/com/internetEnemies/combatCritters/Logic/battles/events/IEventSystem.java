package com.internetEnemies.combatCritters.Logic.battles.events;

/**
 * IEventSystem.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-25
 *
 * @PURPOSE:    Interface for the battle event system
 */
public interface IEventSystem {
    /**
     * @return onPlayCard EventHost
     */
    EventHost<CardEvent> getOnPlayCard();
}
