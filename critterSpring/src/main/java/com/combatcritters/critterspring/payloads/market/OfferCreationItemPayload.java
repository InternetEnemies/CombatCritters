package com.combatcritters.critterspring.payloads.market;

/**
 * OfferCreationItemPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    object for items in offer creation
 */
public record OfferCreationItemPayload(int count, int id, String type) {
}
