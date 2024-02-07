package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;

/**
 * Interface to the inventory of decks in the database
 */
public interface IDeckInventory{

    /**
     * get an IDeck interface for editing of the deck
     * @param deckId id of deck to get
     * @return IDeck for the Deck
     */
    IDeck getDeck(int deckId);

    /**
     * Creates a new Deck and returns an interface for editing
     * @param name name of the deck to be created
     * @return IDeck to the new Deck
     */
    IDeck createDeck(String name);

    /**
     * remove the deck with the given id
     * @param deckId id of deck to delete
     */
    void deleteDeck(int deckId);

    /**
     * get a list of the details related to all the decks
     * @return List of DeckDetails of all the decks in the inventory
     */
    List<DeckDetails> getDeckDetails();
}
