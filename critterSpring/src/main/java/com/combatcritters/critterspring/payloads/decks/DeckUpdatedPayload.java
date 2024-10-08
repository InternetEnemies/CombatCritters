package com.combatcritters.critterspring.payloads.decks;

/**
 * DeckUpdatedPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/7/24
 * 
 * @PURPOSE:    represent a deck updated event
 */
public record DeckUpdatedPayload(DeckPayload deck, DeckValidityPayload deck_validity) {
}
