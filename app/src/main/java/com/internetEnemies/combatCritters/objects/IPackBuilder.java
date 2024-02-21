package com.internetEnemies.combatCritters.objects;
import java.util.List;
import java.util.NavigableMap;

public interface IPackBuilder {
    void setId(int id);
    void setImage(String Image);

    void setName(String Name);
    PackBuilder addSetList(List<Card> cards);

    void addSlot(NavigableMap<Double, Card.Rarity> weightMap);

    Pack build();

}
