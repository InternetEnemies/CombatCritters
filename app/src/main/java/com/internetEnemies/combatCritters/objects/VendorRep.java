package com.internetEnemies.combatCritters.objects;

/**
 * VendorRep.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    holds vendor reputation values for a vendor
 */
public record VendorRep(int currentXp, int level, int nextLevelXp, int prevLevelXp) { }
