package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.listeners.IIntegerEventHandler;

public interface IHealth {
    /**
     * deal damage
     *
     * @param amount amount of damage to deal
     */
    void damage(int amount);

    /**
     * heal the health
     *
     * @param amount amount of health to heal
     */
    void heal(int amount);

    /**
     * @return current health
     */
    int getHealth();

    /**
     * @return eventHandler for heal events
     */
    IIntegerEventHandler getHealEvent();

    /**
     * @return eventHandler for damage events
     */
    IIntegerEventHandler getDamageEvent();

    /**
     * @return eventHandler for change events
     */
    IIntegerEventHandler getChangeEvent();
}
