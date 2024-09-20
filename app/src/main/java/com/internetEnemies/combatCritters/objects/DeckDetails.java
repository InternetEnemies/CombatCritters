/**
 * DeckDetails.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-01-30
 *
 * @PURPOSE:    information of a card
 */

package com.internetEnemies.combatCritters.objects;

import java.io.Serializable;
import java.util.Objects;

public class DeckDetails implements Serializable {
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
