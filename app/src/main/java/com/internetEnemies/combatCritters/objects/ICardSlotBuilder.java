package com.internetEnemies.combatCritters.objects;

import java.util.NavigableMap;

public interface ICardSlotBuilder {
    CardSlotBuilder addProbabilityMap(NavigableMap<Double, Card.Rarity> weightMap);

    CardSlot build();
}
