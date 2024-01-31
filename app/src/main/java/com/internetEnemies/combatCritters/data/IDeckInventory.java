package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Deck;

import java.util.List;

/**
 * Interface to the inventory of decks in the database
 */
public interface IDeckInventory {
    /**
     * get all the decks in the inventory
     * @return List of Deck
     */
    List<Deck> getDecks();

    /**
     * get an IDeck interface for editing of the deck
     * @param deckId id of deck to get
     * @return IDeck for the Deck
     */
    IDeck getIDeck(int deckId);

    /**
     * Creates a new Deck and returns an interface for editing
     * @return IDeck to the new Deck
     */
    IDeck createDeck();

    /**
     * remove the deck with the given id
     * @param deckId id of deck to delete
     */
    void deleteDeck(int deckId);
}
