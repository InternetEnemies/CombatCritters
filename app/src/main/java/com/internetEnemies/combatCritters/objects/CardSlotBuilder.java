/**
 * CardSlotBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-19
 *
 * @PURPOSE:    implementation of ICardSlotBuilder
 */

package com.internetEnemies.combatCritters.objects;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CardSlotBuilder implements ICardSlotBuilder{
    private NavigableMap<Double,Card.Rarity> cardPullChances;
    public CardSlotBuilder(){
        reset();
    }
    public void addProbabilityMap(NavigableMap<Double, Card.Rarity> weightMap){
        cardPullChances.putAll(weightMap);
    }
    public void addProbability(double weight, Card.Rarity rarity){
        cardPullChances.put(weight, rarity);
    }

    @Override
    public void reset() {
        cardPullChances = new TreeMap<>();
    }

    public CardSlot build(){
        return new CardSlot(cardPullChances);
    }


}
