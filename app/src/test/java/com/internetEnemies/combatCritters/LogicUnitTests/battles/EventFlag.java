package com.internetEnemies.combatCritters.LogicUnitTests.battles;

/**
 * EventFlag.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/26/24
 *
 * @PURPOSE:    helper class for testing if an event has been fired
 */
public class EventFlag {
    private boolean flag = false;
    public void fire(){
        flag = true;
    }
    public boolean hasFired() {
        return flag;
    }

}
