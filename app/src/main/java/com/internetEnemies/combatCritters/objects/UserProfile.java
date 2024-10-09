package com.internetEnemies.combatCritters.objects;


/**
 * UserProfile.java
 * COMP 4350
 *
 * @Project Combat Critters 2.0
 * @created 10/9/24
 * @PURPOSE: represent a users profile
 * 
 * @param deckId id of the deck to feature, set to -1 for no featured deck
 */
public record UserProfile(int deckId) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return deckId == that.deckId;
    }

}
