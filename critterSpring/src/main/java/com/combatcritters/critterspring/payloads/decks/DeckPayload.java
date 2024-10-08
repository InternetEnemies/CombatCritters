package com.combatcritters.critterspring.payloads.decks;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * DeckPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/6/24
 * 
 * @PURPOSE:    
 */
public record DeckPayload(List<Integer> cards) {
    public static DeckPayload from(List<Card> cards){
        return new DeckPayload(cards.stream().map(Card::getId).toList());
    }
}
