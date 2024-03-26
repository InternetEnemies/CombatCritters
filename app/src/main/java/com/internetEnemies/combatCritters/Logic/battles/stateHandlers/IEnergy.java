package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IIntegerEventHandler;

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
