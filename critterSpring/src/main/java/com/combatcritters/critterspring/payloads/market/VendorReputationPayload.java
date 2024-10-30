package com.combatcritters.critterspring.payloads.market;

/**
 * VendorReputationPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/30/24
 * 
 * @PURPOSE:    represents a players rep with a vendor for REST
 */
public record VendorReputationPayload(int current_xp, int level, int next_level_xp, int prev_level_xp) {
}
