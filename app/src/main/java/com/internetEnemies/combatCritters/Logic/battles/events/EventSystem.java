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
    private final IEventHost<CardEvent> onPlayCard;
    private final IEventHost<CardEvent> onCardKilled;

    private final IEventHost<CardHealthEvent> onCardDamaged;
    private final IEventHost<CardHealthEvent> onCardHealed;

    public EventSystem() {
        onPlayCard = new EventHost<>();
        onCardKilled = new EventHost<>();
        onCardDamaged = new EventHost<>();
        onCardHealed = new EventHost<>();
    }

    @Override
    public IEventHost<CardEvent> getOnPlayCard() {
        return onPlayCard;
    }

    @Override
    public IEventHost<CardEvent> getOnCardKilled() {
        return onCardKilled;
    }

    @Override
    public IEventHost<CardHealthEvent> getOnCardDamaged() {
        return onCardDamaged;
    }

    @Override
    public IEventHost<CardHealthEvent> getOnCardHealed() {
        return onCardHealed;
    }
}
