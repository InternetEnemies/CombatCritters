package com.internetEnemies.combatCritters.data;

import androidx.annotation.NonNull;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Deck database wrapper
 */
public class DeckStub implements IDeck{
    private final List<Card> cards;
    private final DeckDetails deckDetails;

    public DeckStub(DeckDetails deckDetails){
        this.deckDetails = deckDetails;
        this.cards = new ArrayList<>();
    }

    @Override
    public Card getCard(int slot) throws IndexOutOfBoundsException {
        return cards.get(slot);
    }

    @Override
    public void addCard(int slot, Card card) {
        cards.add(slot,card);
    }

    @Override
    public void removeCard(int slot) {
        cards.remove(slot);
    }

    @Override
    public int countCard(Card card) {

        return countCards().getOrDefault(card,0);
    }

    @Override
    public Map<Card, Integer> countCards() {
        //with sql this is not how this will be done

        Map<Card,Integer> counts = new HashMap<>();
        for (Card card : cards) {
            counts.put(card, counts.getOrDefault(card,0) + 1); // put 1 or count + 1
        }

        return counts;
    }

    @Override
    public DeckDetails getInfo() {
        return deckDetails;
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(new ArrayList<>(cards));
    }

    @NonNull
    @Override
    public Iterator<Card> iterator() {
        return new ArrayList<>(cards).iterator();
    }
}
