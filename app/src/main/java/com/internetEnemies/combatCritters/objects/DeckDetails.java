package com.internetEnemies.combatCritters.objects;

public class DeckDetails {
    private final int id;
    private final String name;
    public DeckDetails(int id, String name) {
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
