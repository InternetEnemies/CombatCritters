package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardInventoryStub implements ICardInventory{
    private final Map<Card,Integer> cardDB;

    public CardInventoryStub(){
        cardDB = new HashMap<>();
    }

    @Override
    public int getCardAmount(Card card) throws NullPointerException{
        Integer count = cardDB.getOrDefault(card, 0);

        assert count != null; // If this happens the map is in a bad state
        return count;
    }

    @Override
    public Map<Card, Integer> getCards() {
        //Deep copy of in memory db
        return new HashMap<>(cardDB);
    }

    @Override
    public void addCard(Card card) {
        cardDB.put(card, getCardAmount(card) + 1);
    }

    @Override
    public void addCards(List<Card> cards) {
        for ( Card card : cards) {
            addCard(card);
        }
    }

    @Override
    public void removeCard(Card card, int amount) {
        int count = getCardAmount(card);
        if (count <= amount){
            cardDB.remove(card);
        } else {
            cardDB.put(card, count-amount);
        }
    }

    @Override
    public void removeCard(Card card) {
        removeCard(card, 1);
    }
}
