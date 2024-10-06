package com.combatcritters.critterspring.payloads.decks;

import java.util.Optional;

/**
 * DeckDetailsPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/5/24
 * 
 * @PURPOSE:    provide the minimum details for a deck
 */
public record DeckDetailsPayload(Optional<Integer> deckid, String name) {
}
