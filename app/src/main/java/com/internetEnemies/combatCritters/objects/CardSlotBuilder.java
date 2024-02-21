package com.internetEnemies.combatCritters.objects;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CardSlotBuilder implements ICardSlotBuilder{
    private final NavigableMap<Double,Card.Rarity> cardPullChances;
    public CardSlotBuilder(){
        cardPullChances = new TreeMap<>();
    }
    public CardSlotBuilder addProbabilityMap(NavigableMap<Double, Card.Rarity> weightMap){
        cardPullChances.putAll(weightMap);
        return this;
    }
    public void addProbability(double weight, Card.Rarity rarity){
        cardPullChances.put(weight, rarity);
    }
    public CardSlot build(){
        return new CardSlot(cardPullChances);
    }


}
