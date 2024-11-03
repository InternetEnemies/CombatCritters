package com.combatcritters.critterspring.payloads.market;

/**
 * RepChangePayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    reputation change payload for rest
 */
public record RepChangePayload(int amount, int vendor) {
}
