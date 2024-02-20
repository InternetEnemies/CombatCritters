package com.internetEnemies.combatCritters.objects;
import java.util.List;
import java.util.NavigableMap;

public interface IPackBuilder {
    PackBuilder addSetList(List<Card> cards);
    void addSlot(NavigableMap<Double, Card.Rarity> weightMap);

    Pack build();

}
