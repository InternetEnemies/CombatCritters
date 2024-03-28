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
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.ArrayList;
import java.util.List;

public class DeckManager implements IDeckManager {

    private final IDeckInventory deckInventory;
    private final ICardInventory cardInventory; // this is for creating validators
    private final IDeckValidator deckValidator;

    /**
     * Constructor for DeckManager class
     */
    public DeckManager() {
        this(Database.getInstance().getDeckInventory(), Database.getInstance().getCardInventory(), new DeckValidator());
    }

    /**
     * Test Constructor for DeckManager class with assigned deckInventory
     */
    public DeckManager(IDeckInventory deckInventory, ICardInventory cardInventory, DeckValidator deckValidator) {
        assert deckInventory != null;
        //testing constructor
        this.deckInventory = deckInventory;
        this.cardInventory = cardInventory;
        this.deckValidator = deckValidator;
    }

    @Override
    public DeckDetails createDeck(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (name.length() > 15) {
            throw new IllegalArgumentException("Name must be 20 characters or less");
        }
        List<DeckDetails> currDecks = getDecks();
        for (int i = 0; i < currDecks.size(); i++) {
            if (currDecks.get(i).getName().equals(name)) {
                throw new IllegalArgumentException("Name cannot be the same as an existing deck");
            }
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
        return new DeckBuilder(deck, new DeckValidator(this.cardInventory));
    }

    @Override
    public List<DeckDetails> getDecks() {
        return deckInventory.getDeckDetails();
    }

    @Override
    public List<DeckDetails> getValidDecks() {
        List<DeckDetails> decks = deckInventory.getDeckDetails();
        List<DeckDetails> validDecks = new ArrayList<>();

        for(DeckDetails deck : decks) {
            IDeck iDeck = deckInventory.getDeck(deck);
            if(deckValidator.validate(iDeck).isValid()) {
                validDecks.add(deck);
            }
        }
        return validDecks;
    }
}
