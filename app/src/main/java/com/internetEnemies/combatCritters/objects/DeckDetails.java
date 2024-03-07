package com.internetEnemies.combatCritters.objects;

import androidx.annotation.NonNull;

import java.util.Objects;

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

    @NonNull
    @Override
    public String toString() {return name;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckDetails)) return false;
        DeckDetails that = (DeckDetails) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
