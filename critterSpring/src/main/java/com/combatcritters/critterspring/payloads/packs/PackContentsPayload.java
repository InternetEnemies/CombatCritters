package com.combatcritters.critterspring.payloads.packs;

import com.combatcritters.critterspring.payloads.CardPayload;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * PackContentsPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/25/24
 * 
 * @PURPOSE:    provide Pack Contents payload representation
 */
public record PackContentsPayload(List<CardPayload<?>> cards) {
    public static PackContentsPayload from(List<Card> cards) {
        List<CardPayload<?>> cardPayloads = new ArrayList<>();
        for (Card card : cards) {
            cardPayloads.add(CardPayload.from(card));
        }
        return new PackContentsPayload(cardPayloads);
    }
}
