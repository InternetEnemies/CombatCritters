package com.combatcritters.critterspring.payloads.decks;

public record DeckValidityRulesPayload (int min_cards, int max_cards, int limit_legend, int limit_epic, int limit_rare, int limit_item){
}
