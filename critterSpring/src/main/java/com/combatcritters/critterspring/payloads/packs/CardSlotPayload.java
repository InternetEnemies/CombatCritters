package com.combatcritters.critterspring.payloads.packs;

import com.internetEnemies.combatCritters.Logic.inventory.packs.CardSlotBuilder;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;

import java.util.List;

/**
 * CardSlotPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/27/24
 * 
 * @PURPOSE:    represent card slots for rest
 */
public record CardSlotPayload(List<RarityWeightPayload> rarityWeights) {
    public CardSlot toCardSlot(){
        CardSlotBuilder cardSlotBuilder = new CardSlotBuilder();
        for (RarityWeightPayload rarityWeightPayload : rarityWeights) {
            cardSlotBuilder.addProbability(rarityWeightPayload.weight(), Card.Rarity.values()[rarityWeightPayload.rarity()]);
        }
        return cardSlotBuilder.build();
    }
}
