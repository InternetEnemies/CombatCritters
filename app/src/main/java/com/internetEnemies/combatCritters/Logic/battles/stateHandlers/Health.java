package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IIntegerEventHandler;
import com.internetEnemies.combatCritters.Logic.battles.events.integerEvents.IntegerEventHandler;

/**
 * Health.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    implementation of IHealth
 */

public class Health implements IHealth {
    private int health;
    private final int maxHealth;

    private final IIntegerEventHandler changeEvent;
    private final IIntegerEventHandler damageEvent;
    private final IIntegerEventHandler healEvent;

    public Health(int max, int init, IIntegerEventHandler changeEvent, IIntegerEventHandler damageEvent, IIntegerEventHandler healEvent) {
        this.health = init;
        this.maxHealth = max;

        this.changeEvent = changeEvent;
        this.damageEvent = damageEvent;
        this.healEvent = healEvent;
    }
    public Health(int max, int init) {
        this(max,init, new IntegerEventHandler(),new IntegerEventHandler(), new IntegerEventHandler());
    }

    @Override
    public void damage(int amount) {
        assert amount > 0;
        setHealth(health - amount);
        this.damageEvent.fireEvent(amount);
    }

    @Override
    public void heal(int amount){
        assert amount > 0;
        setHealth(health + amount);
        this.healEvent.fireEvent(amount);
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public IIntegerEventHandler getHealEvent() {
        return this.healEvent;
    }

    @Override
    public IIntegerEventHandler getDamageEvent() {
        return this.damageEvent;
    }

    @Override
    public IIntegerEventHandler getChangeEvent() {
        return this.changeEvent;
    }

    /**
     * set the value of health, value is clamped to class params
     * @param amount to set the health to
     */
    private void setHealth(int amount){
        this.health = Math.max(0, Math.min(maxHealth,amount));// Clamp health value
        this.changeEvent.fireEvent(this.health);
    }
}
