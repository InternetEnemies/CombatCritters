/**
 * IDeckBuilder.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     01-February-2024
 *
 * PURPOSE:     An interface for Deck builder
 */

package com.internetEnemies.combatCritters.Logic;

public interface IDeckBuilder {

    /**
     * Creates a new Deck and store the deckId in "givenIndex"
     * @param name name of the deck to be created
     * @param givenIndex should be an empty int variable since the index of the new deck will be stored there
     * @return
     */
    boolean createNewDeck(String name, int givenIndex);


}
