package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IIntegerEventHandler;
import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IntegerEventHandler;

public class Energy implements IEnergy {
    private int energy;
    private final int maxEnergy;
    private final IIntegerEventHandler changeEvent;
    public Energy(int max, int init, IIntegerEventHandler changeEvent) {
        this.energy = init;
        this.maxEnergy = max;
        this.changeEvent = changeEvent;
    }

    public Energy(int max, int init) {
        this(max, init, new IntegerEventHandler());
    }

    @Override
    public void addEnergy(int amount){
        assert amount > 0;
        setEnergy(this.energy + amount);
    }

    @Override
    public void removeEnergy(int amount){
        assert amount > 0;
        setEnergy(this.energy - amount);
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public IIntegerEventHandler getEvent() {
        return changeEvent;
    }


    /**
     * set the energy and clamp is to the constraint
     * @param amount amount to set to
     */
    private void setEnergy(int amount) {
        this.energy = Math.max(0, Math.min(maxEnergy,amount));// Clamp energy value
        this.changeEvent.fireEvent(this.energy);
    }
}
