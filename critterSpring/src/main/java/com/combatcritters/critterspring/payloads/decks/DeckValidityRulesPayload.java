package com.combatcritters.critterspring.payloads.decks;

import com.internetEnemies.combatCritters.Logic.inventory.decks.DeckValidator;

/**
 * DeckValidityRulesPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/7/24
 * 
 * @PURPOSE:    payload for deck rules
 */
public record DeckValidityRulesPayload (int min_cards, int max_cards, int limit_legend, int limit_epic, int limit_rare, int limit_item){
    //! if we ever have other settings we'll have to change this but for now this should be fine
    public static DeckValidityRulesPayload getDefault(){
        return new DeckValidityRulesPayload(
                DeckValidator.MIN_CARDS,
                DeckValidator.MAX_CARDS,
                DeckValidator.LIMIT_LEGEND,
                DeckValidator.LIMIT_EPIC,
                DeckValidator.LIMIT_RARE,
                DeckValidator.LIMIT_ITEM
        );
    }
}
