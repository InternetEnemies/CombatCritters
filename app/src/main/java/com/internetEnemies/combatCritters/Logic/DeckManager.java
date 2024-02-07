/**
 * DeckManager.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     06-February-2024
 *
 * @PURPOSE:     Manage the deck Inventory, functions include createDeck and deleteDeck, etc,
 *               provide DeckBuilder class for each deck using getBuilder
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.ArrayList;
import java.util.List;

public class DeckManager {

    private final IDeckInventory deckInventory;

    /**
     * Constructor for DeckManager class
     */
    public DeckManager() {
        this(Database.getInstance().getDeckInventory());
    }

    /**
     * Test Constructor for DeckManager class with assigned deckInventory
     */
    public DeckManager(IDeckInventory deckInventory) {
        if(deckInventory == null) {
            throw new NullPointerException();
        }
        //testing constructor
        this.deckInventory = deckInventory;
    }

    /**
     * Creates a new Deck into deckInventory
     *
     * @param name name of the deck to be created
     * @return DeckDetails of the deck if the deck successfully created
     */
    public DeckDetails createDeck(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        //create the deck in the inventory
        IDeck deck = deckInventory.createDeck(name);
        return deck.getInfo();
    }

    /**
     * Delete the Deck with given DeckDetails
     *
     * @param deckInfo the DeckDetails of the deck wanted to delete
     */
    public void deleteDeck(DeckDetails deckInfo) {
        if (deckInfo == null) {
            throw new NullPointerException();
        }
        deckInventory.deleteDeck(deckInfo.getId());
    }

    /**
     * Return a DeckBuilder object for modifying the deck
     *
     * @param deckInfo the DeckDetails of the deck wanted to modify with
     * @return a DeckBuilder object of the deck
     */
    public DeckBuilder getBuilder(DeckDetails deckInfo) {
        if (deckInfo == null) {
            throw new NullPointerException();
        }
        IDeck deck;
        deck = deckInventory.getDeck(deckInfo.getId());
        return new DeckBuilder(deck);
    }

    /**
     * get the list of the decks for further purpose
     *
     * @return A list of DeckDetails of current stored decks
     */
    public List<DeckDetails> getDecks() {
        List<DeckDetails> list = new ArrayList<>();
        for(IDeck deck : deckInventory) {
            list.add(deck.getInfo());
        }
        return list;
    }
}
