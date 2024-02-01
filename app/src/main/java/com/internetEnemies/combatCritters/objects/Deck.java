package com.internetEnemies.combatCritters.objects;

import java.util.List;

public class Deck {
    private final int id;
    private final String name;
    private final List<Card> cards;
    public Deck(int id, String name, List<Card> cards) {
        this.id = id;
        this.name = name;
        this.cards = cards;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

}
