package com.internetEnemies.combatCritters.data;


import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CardsStub implements IRegistry<Card>{
    Map<Integer, Card> allCards;

    public CardsStub(Map<Integer, Card> ac) {
        allCards = ac;
    }

    @Override
    public Card getSingle(int id) {

        for (Map.Entry<Integer, Card> entry: allCards.entrySet()) {
            if (entry.getKey() == id){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public List<Card> getListOf(List<Integer> ids) {
        List<Card> resultSet = new ArrayList<>();
        for (int i: ids) {
            resultSet.add(allCards.get(i));
        }
        return resultSet;
    }

    @Override
    public List<Card> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(allCards.values()));
    }
}
