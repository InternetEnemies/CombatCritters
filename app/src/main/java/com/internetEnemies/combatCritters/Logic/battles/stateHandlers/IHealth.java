package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IIntegerEventHandler;

/**
 * IHealth.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    interface of managing health of a card object
 */

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
     * events fired by this handler have the amount the health was healed
     * @return eventHandler for heal events
     */
    IIntegerEventHandler getHealEvent();

    /**
     * events fired by this handler have the amount the health was damaged
     * @return eventHandler for damage events
     */
    IIntegerEventHandler getDamageEvent();

    /**
     * events fired by this handler have the health after the change
     * @return eventHandler for change events
     */
    IIntegerEventHandler getChangeEvent();
}
