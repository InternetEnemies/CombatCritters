package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IIntegerEventHandler;

/**
 * IEnergy.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    Interface of managing energy of card object
 */

public interface IEnergy {
    /**
     * add energy to the energy pool
     *
     * @param amount amount of energy to add
     */
    void addEnergy(int amount);

    /**
     * remove energy from the pool
     *
     * @param amount amount of energy to remove
     */
    void removeEnergy(int amount);

    /**
     * @return current energy level
     */
    int getEnergy();

    /**
     * @return return the event for whenever the energy changes
     */
    IIntegerEventHandler getEvent();
}
