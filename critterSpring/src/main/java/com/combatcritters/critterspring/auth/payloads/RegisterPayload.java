package com.combatcritters.critterspring.auth.payloads;

/**
 * RegisterPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     9/28/24
 * 
 * @PURPOSE:    represent a user registration payload 
 */
public record RegisterPayload(String username, String password) {
}
