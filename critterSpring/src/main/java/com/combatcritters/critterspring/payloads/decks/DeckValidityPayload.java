package com.combatcritters.critterspring.payloads.decks;

import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.util.List;

/**
 * DeckValidityPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/7/24
 * 
 * @PURPOSE:    payload for deck validity
 */
public record DeckValidityPayload(boolean isvalid, List<String> issues) {
    public static DeckValidityPayload from(DeckValidity validity) {
        return new DeckValidityPayload(validity.isValid(), validity.getIssues());
    }
}
