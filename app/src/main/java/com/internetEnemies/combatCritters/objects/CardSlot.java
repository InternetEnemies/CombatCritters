package com.internetEnemies.combatCritters.objects;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CardSlot {
    private final NavigableMap<Double,Card.Rarity> cardPullChances = new TreeMap<>(); //Stores Weight as key and card rarity (ex. [Rare, 25])

    public CardSlot(NavigableMap<Double, Card.Rarity> chances) {
        for (Map.Entry value: chances.entrySet()) {
            double sum = (double)value.getKey();

            cardPullChances.put(sum, (Card.Rarity)value.getValue());
        }

    }

    public NavigableMap<Double,Card.Rarity> getCardPullChances(){
        return cardPullChances;
    }

}
