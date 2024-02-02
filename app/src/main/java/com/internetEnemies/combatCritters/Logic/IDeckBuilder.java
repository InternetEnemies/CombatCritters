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
     * @return true if the deck successfully created, false if the an error appear and the deck is not created
     */
    boolean createNewDeck(String name);

    /**
     * Delete the Deck with given Index
     * @param deckIndex the index of the deck wanted to delete
     * @return true if the deck successfully deleted, false if the an error appear and the deck still remains
     */
    boolean deleteExistingDeck(int deckIndex);
}
