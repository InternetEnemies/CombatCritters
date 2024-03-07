/**
 * DeckDetails.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-01-30
 *
 * @PURPOSE:    information of a card
 */

package com.internetEnemies.combatCritters.objects;

import androidx.annotation.NonNull;

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
}
