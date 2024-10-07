package com.combatcritters.critterspring.payloads.decks;

import com.internetEnemies.combatCritters.objects.DeckDetails;

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
    static DeckDetailsPayload from(DeckDetails deckDetails) {
        return new DeckDetailsPayload(Optional.of(deckDetails.getId()), deckDetails.getName());
    }
}
