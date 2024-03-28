package com.internetEnemies.combatCritters.LogicUnitTests.battles.stateHandlers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Health;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.EventFlag;

/**
 * HealthTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/26/24
 *
 * @PURPOSE:    Test health object
 */
public class HealthTest {
    @Before
    public void setup() {

    }

    @Test
    public void testDamageHeal() {
        Health health = new Health(5, 5);
        assertEquals(5, health.getHealth());
        health.damage(2);
        assertEquals(3,health.getHealth());
        health.heal(1);
        assertEquals(4, health.getHealth());

        health.damage(100);
        assertEquals(0, health.getHealth());
        health.heal(100);
        assertEquals(5, health.getHealth());
    }

    @Test
    public void testEvents() {
        Health health = new Health(5, 5);
        EventFlag healFlag = new EventFlag();
        EventFlag damageFlag = new EventFlag();
        EventFlag changeFlag = new EventFlag();
        health.getChangeEvent().subscribe(e -> changeFlag.fire());
        health.getDamageEvent().subscribe(e -> damageFlag.fire());
        health.getHealEvent().subscribe(e -> healFlag.fire());

        assertEquals(5, health.getHealth());
        health.damage(2);
        assertEquals(3,health.getHealth());
        health.heal(1);
        assertEquals(4, health.getHealth());

        assertTrue(healFlag.hasFired());
        assertTrue(damageFlag.hasFired());
        assertTrue(changeFlag.hasFired());
    }
}
