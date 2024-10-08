/**
 * IDeckInventory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-01-30
 *
 * @PURPOSE:    Interface to the inventory of decks in the database
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;

public interface IDeckInventory{

    /**
     * get an IDeck interface for editing of the deck
     * @param deckDetails details of deck to get
     * @return IDeck for the Deck
     */
    IDeck getDeck(DeckDetails deckDetails);

    /**
     * Creates a new Deck and returns an interface for editing
     * @param name name of the deck to be created
     * @return IDeck to the new Deck
     */
    IDeck createDeck(String name);

    /**
     * remove the deck with the given id
     * @param deckDetails details of deck to delete
     */
    void deleteDeck(DeckDetails deckDetails);

    /**
     * get a list of the details related to all the decks
     * @return List of DeckDetails of all the decks in the inventory
     */
    List<DeckDetails> getDeckDetails();
    /**
     * get deck details from id
     * @param id id of the details to get
     * @return deck details with the id if they exist
     */
    DeckDetails getDeckDetails(int id);
}
