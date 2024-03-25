package com.internetEnemies.combatCritters.Logic.battles.events;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @param <T> type being returned by the events
 */
public class EventHost<T> { //todo
    private final List<EventListener<T>> listeners;

    public EventHost() {
        listeners = new LinkedList<>();
    }

    public void subscribe(EventListener<T> listener) {
        listeners.add(listener);
    }

    public void fireEvent(T payload) {
        listeners.forEach(listener -> listener.onEvent(payload));
    }
}
