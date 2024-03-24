package com.internetEnemies.combatCritters.Logic.battles.stateHandlers.listeners;

import java.util.LinkedList;
import java.util.List;

/**
 * IntegerEventHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    handle integer change events
 */
public class IntegerEventHandler implements IIntegerEventHandler {
    private final List<IIntegerListener> listeners;
    public IntegerEventHandler() {
        this.listeners = new LinkedList<>();
    }

    @Override
    public void subscribe(IIntegerListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void fireEvent(int i) {
        listeners.forEach(listener -> listener.onChange(i));
    }
}
