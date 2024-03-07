/**
 * DeckManager.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-February-2024
 *
 * @PURPOSE:     Manage the deck Inventory, functions include createDeck and deleteDeck, etc,
 *               provide DeckBuilder class for each deck using getBuilder
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.List;

public class DeckManager implements IDeckManager {

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
        assert deckInventory != null;
        //testing constructor
        this.deckInventory = deckInventory;
    }

    @Override
    public DeckDetails createDeck(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        //create the deck in the inventory
        IDeck deck = deckInventory.createDeck(name);
        return deck.getInfo();
    }

    @Override
    public void deleteDeck(DeckDetails deckInfo) {
        assert deckInfo != null;
        deckInventory.deleteDeck(deckInfo);
    }

    @Override
    public IDeckBuilder getBuilder(DeckDetails deckInfo) {
        assert deckInfo != null;
        IDeck deck;
        deck = deckInventory.getDeck(deckInfo);
        return new DeckBuilder(deck);
    }

    @Override
    public List<DeckDetails> getDecks() {
        return deckInventory.getDeckDetails();
    }
}
