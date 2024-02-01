package com.internetEnemies.combatCritters.objects;

import java.util.NavigableMap;

public class CardSlot {
    private final NavigableMap<Double,Card.Rarity> cardPullChances; //Stores Weight as key and card rarity (ex. [Rare, 25])

    public CardSlot(NavigableMap<Double, Card.Rarity> cardPullChances) {
        this.cardPullChances = cardPullChances;
    }

    public NavigableMap<Double,Card.Rarity> getCardPullChances(){
        return cardPullChances;
    }

    public double getWeightSum(){
        double sum = 0;
        for (double key : cardPullChances.keySet()){
            sum += key;
        }
        return sum;
    }
}
