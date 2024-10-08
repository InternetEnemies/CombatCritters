package com.combatcritters.critterspring.payloads.decks;

import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.util.List;

public record DeckValidityPayload(boolean isvalid, List<String> issues) {
    public static DeckValidityPayload from(DeckValidity validity) {
        return new DeckValidityPayload(validity.isValid(), validity.getIssues());
    }
}
