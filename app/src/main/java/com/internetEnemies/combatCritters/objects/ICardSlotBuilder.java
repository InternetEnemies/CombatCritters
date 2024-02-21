package com.internetEnemies.combatCritters.objects;

import java.util.NavigableMap;

public interface ICardSlotBuilder {
    CardSlotBuilder addProbabilityMap(NavigableMap<Double, Card.Rarity> weightMap);
    void addProbability(double weight, Card.Rarity rarity);

    CardSlot build();
}
