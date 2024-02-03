package com.internetEnemies.combatCritters.data;

import androidx.annotation.NonNull;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CardsStub implements IRegistry<Card>{
    List<Card> allCards;

    public CardsStub(List<Card> ac) {
        allCards = ac;
    }

    @Override
    public Card getSingle(int id) {

        for (Card i: allCards) {
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }
    public List<Card> getListOf(List<Integer> ids) {
        List<Card> resultSet = new ArrayList<>();
        for (int i: ids) {
            List<Card> cardsWithID = allCards.stream().filter(p -> p.getId() == i).collect(Collectors.toList());
            resultSet.addAll(cardsWithID);
        }
        return resultSet;
    }

    @NonNull
    @Override
    public Iterator<Card> iterator() {
        return new ArrayList<>(allCards).iterator();

    }
}
