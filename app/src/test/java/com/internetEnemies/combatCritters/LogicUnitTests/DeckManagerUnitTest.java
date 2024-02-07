package com.internetEnemies.combatCritters.LogicUnitTests;

import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.data.DeckInventoryStub;
import com.internetEnemies.combatCritters.data.DeckStub;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

public class DeckManagerUnitTest {
    private DeckManager deckManager;
    private IDeckInventory stubDeckInventory;

    @Before
    public void setUp() {
        stubDeckInventory = new DeckInventoryStub();
        deckManager = new DeckManager(stubDeckInventory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDeck_EmptyName() {
        deckManager.createDeck("");
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteDeck_NullDeckDetails() {
        deckManager.deleteDeck(null);
    }

    @Test
    public void testDeleteDeck() {
        int deckId = 1;
        DeckDetails deckDetails = new DeckDetails(deckId, "Test Deck");

        deckManager.deleteDeck(deckDetails);

        assertNull(stubDeckInventory.getDeck(deckId));
    }

    @Test(expected = NullPointerException.class)
    public void testGetBuilder_NullDeckDetails() {
        deckManager.getBuilder(null);
    }

    @Test
    public void testGetDecks() {
        // Create the expected deck details
        DeckDetails expectedDeck1 = new DeckDetails(1, "Deck 1");
        DeckDetails expectedDeck2 = new DeckDetails(2, "Deck 2");

        // Create the decks in the stub deck inventory
        stubDeckInventory.createDeck("Deck 1");
        stubDeckInventory.createDeck("Deck 2");

        // Get the actual decks from the deck manager
        List<DeckDetails> actualDecks = deckManager.getDecks();

        // Assert that the actual deck list size matches the expected size
        assertEquals(2, actualDecks.size());

        // Assert that each expected deck is contained within the actual list
        assertTrue(actualDecks.contains(expectedDeck1));
        assertTrue(actualDecks.contains(expectedDeck2));
    }
}
