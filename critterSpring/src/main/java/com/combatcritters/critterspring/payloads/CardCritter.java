package com.combatcritters.critterspring.payloads;

import java.util.List;

/**
 * CardCritter.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/4/24
 * 
 * @PURPOSE:    Critter card payload
 */
public record CardCritter(int damage, int health, List<Integer> abilities) {
}
