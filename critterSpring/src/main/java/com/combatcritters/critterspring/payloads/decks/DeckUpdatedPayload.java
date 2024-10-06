package com.combatcritters.critterspring.payloads.decks;

public record DeckUpdatedPayload(DeckPayload deck, DeckValidityPayload deck_validity) {
}
