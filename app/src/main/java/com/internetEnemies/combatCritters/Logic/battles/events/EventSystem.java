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
    private static EventSystem INSTANCE;

    private final EventHost<CardEvent> onPlayCard;

    private EventSystem() {
        onPlayCard = new EventHost<>();
    }

    public static synchronized IEventSystem getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EventSystem();
        }
        return INSTANCE;
    }

    @Override
    public EventHost<CardEvent> getOnPlayCard() {
        return onPlayCard;
    }
}
