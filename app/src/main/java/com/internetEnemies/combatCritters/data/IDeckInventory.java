package com.internetEnemies.combatCritters.data;

/**
 * Interface to the inventory of decks in the database
 */
public interface IDeckInventory extends Iterable<IDeck>{

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
}
