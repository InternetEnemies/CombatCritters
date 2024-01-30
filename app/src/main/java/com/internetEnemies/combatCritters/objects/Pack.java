package com.internetEnemies.combatCritters.objects;

import java.util.NavigableMap;

public class Pack {
    private final int id;
    private final String name;
    private final String image;
    private final NavigableMap<Double,Card> cards; // map of cards (weight as key for card)

    public Pack(int id, String name, String image, NavigableMap<Double,Card> cards) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.cards = cards;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }


}