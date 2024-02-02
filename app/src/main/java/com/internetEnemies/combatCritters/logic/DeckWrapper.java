package com.internetEnemies.combatCritters.logic;

import androidx.annotation.NonNull;

import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class DeckWrapper implements Iterable<Card> {
    private final IDeck deckDB;
    public DeckWrapper(IDeck deckDB){
        this.deckDB = deckDB;
    }

    public Card getCard(int slot) {
        return deckDB.getCard(slot);
    }

    public void addCard(int slot, Card card){
        deckDB.addCard(slot,card);
    }

    public void removeCard(int slot) {
        deckDB.removeCard(slot);
    }

    public int countCard(Card card) {
        return deckDB.countCard(card);
    }

    public Map<Card,Integer> countCards() {
        return deckDB.countCards();
    }

    @NonNull
    @Override
    public Iterator<Card> iterator() {
        return new ArrayList<>(deckDB.getCards()).iterator();
    }
}
