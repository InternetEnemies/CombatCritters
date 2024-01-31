package com.internetEnemies.combatCritters.objects;

import java.util.List;

public class Deck {
    private final int id;
    private final String name;
    public Deck(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
