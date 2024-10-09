package com.internetEnemies.combatCritters.objects;

import java.util.Objects;

/**
 * UserProfile.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    represent a users profile
 */
public class UserProfile {
    private final int deckId;

    public UserProfile(int deckId) {
        this.deckId = deckId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return deckId == that.deckId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(deckId);
    }

    public int getDeckId() {
        return deckId;
    }
}
