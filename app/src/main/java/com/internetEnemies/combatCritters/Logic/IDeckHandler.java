/**
 * IDeckHandler.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     04-February-2024
 *
 * PURPOSE:     An interface for Deck Handler, handle the deck inventory,
 *              creating, removing, searching decks
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;

public interface IDeckHandler {
    /**
     * @Private
     * @Properties a final IDeckInventory reference, pass-in from DeckHandler
     */

    /**
     * Creates a new Deck into deckInventory
     * @param name name of the deck to be created
     * @return true if the deck successfully created, false if the an error appear and the deck is not created
     */
    boolean createDeck(String name);

    /**
     * Delete the Deck with given DeckDetails
     * @param deckInfo the DeckDetails of the deck wanted to delete
     * @return true if the deck successfully deleted, false if the an error appear and the deck still remains
     */
    boolean deleteDeck(DeckDetails deckInfo);

    /**
     *  get the list of the decks for further purpose
     * @return A list of DeckDetails of current stored decks, null if nothing inside the deckInventory
     */
    List<DeckDetails> getDecks();

    /**
     *  pass in the inventory to the deck builder
     * @return the reference for the deckInventory in DeckHandler
     */
    IDeckInventory getInventory();
}
