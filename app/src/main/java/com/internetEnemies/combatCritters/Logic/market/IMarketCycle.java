package com.internetEnemies.combatCritters.Logic.market;

/**
 * IMarketCycle.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/17/24
 * 
 * @PURPOSE:    provide interface for cycling vendors
 */
public interface IMarketCycle {
    /**
     * run cycle operation for a vendor
     * @param vendorId id of the vendor to cycle
     */
    void runCycle(int vendorId);
}
