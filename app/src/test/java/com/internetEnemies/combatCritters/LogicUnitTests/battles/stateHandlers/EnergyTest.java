package com.internetEnemies.combatCritters.LogicUnitTests.battles.stateHandlers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.Energy;
import com.internetEnemies.combatCritters.LogicUnitTests.battles.EventFlag;

public class EnergyTest {
    @Before
    public void setup() {

    }

    @Test
    public void testAddRemoveEnergy() {
        Energy energy = new Energy(5,0);
        energy.addEnergy(2);
        assertEquals(2,energy.getEnergy());
        energy.removeEnergy(1);
        assertEquals(1, energy.getEnergy());
        energy.addEnergy(100);
        assertEquals(5,energy.getEnergy());
        energy.removeEnergy(200);
        assertEquals(0,energy.getEnergy());
    }

    @Test
    public void testEnergyEvent() {
        Energy energy = new Energy(5,0);
        EventFlag flag = new EventFlag();
        energy.getEvent().subscribe(num -> flag.fire());
        energy.addEnergy(1);
        assertTrue(flag.hasFired());
    }
}
