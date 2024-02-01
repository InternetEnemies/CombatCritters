package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckStub implements IDeck{
    private final List<Card> cards;
    private final Deck deck;

    public DeckStub(Deck deck){
        this.deck = deck;
        this.cards = new ArrayList<>();
    }


    @Override
    public List<Card> getCards() {
        // return copy of cards list
        return new ArrayList<>(cards);
    }

    @Override
    public Card getCard(int slot) throws IndexOutOfBoundsException {
        return cards.get(slot);
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
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
    public Deck getInfo() {
        return deck;
    }
}
