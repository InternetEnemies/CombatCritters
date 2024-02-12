package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;

public interface IDeckManager {
    /**
     * Creates a new Deck into deckInventory
     *
     * @param name name of the deck to be created
     * @return DeckDetails of the deck if the deck successfully created
     */
    DeckDetails createDeck(String name);

    /**
     * Delete the Deck with given DeckDetails
     *
     * @param deckInfo the DeckDetails of the deck wanted to delete
     */
    void deleteDeck(DeckDetails deckInfo);

    /**
     * Return a DeckBuilder object for modifying the deck
     *
     * @param deckInfo the DeckDetails of the deck wanted to modify with
     * @return a DeckBuilder object of the deck
     */
    IDeckBuilder getBuilder(DeckDetails deckInfo);

    /**
     * get the list of the decks for further purpose
     *
     * @return A list of DeckDetails of current stored decks
     */
    List<DeckDetails> getDecks();
}
