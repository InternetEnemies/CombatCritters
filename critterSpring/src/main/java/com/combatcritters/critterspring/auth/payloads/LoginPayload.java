package com.combatcritters.critterspring.auth.payloads;

/**
 * LoginPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/28/24
 * 
 * @PURPOSE:    represent a user login payload
 */
public record LoginPayload(String username, String password) {
}
