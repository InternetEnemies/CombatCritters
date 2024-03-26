package com.internetEnemies.combatCritters.Logic.battles.events;

/**
 * EventSystem.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-25
 *
 * @PURPOSE:    provide events to battles
 *
 */
public class EventSystem implements IEventSystem {
    private final EventHost<CardEvent> onPlayCard;

    public EventSystem() {
        onPlayCard = new EventHost<>();
    }

    @Override
    public EventHost<CardEvent> getOnPlayCard() {
        return onPlayCard;
    }
}
