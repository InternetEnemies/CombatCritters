package com.combatcritters.critterspring.payloads.packs;

import com.combatcritters.critterspring.payloads.CardPayload;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * PackContentsPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/25/24
 * 
 * @PURPOSE:    provide Pack opening result payload representation
 */
public record PackResultPayload(List<CardPayload<?>> cards) {
    public static PackResultPayload from(List<Card> cards) {
        return new PackResultPayload(CardPayload.fromList(cards));
    }
}
