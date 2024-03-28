package com.internetEnemies.combatCritters.Logic.battles.events;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @param <T> type being returned by the events
 */
public class EventHost<T> implements IEventHost<T> {
    private final List<EventListener<T>> listeners;

    public EventHost() {
        listeners = new LinkedList<>();
    }

    @Override
    public void subscribe(EventListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void fireEvent(T payload) {
        listeners.forEach(listener -> listener.onEvent(payload));
    }
}
