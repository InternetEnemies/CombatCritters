package com.combatcritters.critterspring.payloads.decks;

import java.util.List;

public record DeckValidityPayload(boolean isvalid, List<String> issues) {
}
