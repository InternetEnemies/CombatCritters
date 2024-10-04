package com.combatcritters.critterspring.payloads;

/**
 * ItemStackPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/4/24
 * 
 * @PURPOSE:    represents a stack of items
 */
public record ItemStackPayload<T>(
        int count,
        T item
) {
}
