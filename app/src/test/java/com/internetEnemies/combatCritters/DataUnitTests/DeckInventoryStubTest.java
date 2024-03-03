package com.internetEnemies.combatCritters.DataUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.DeckDetails;

public class DeckInventoryStubTest {
    DeckInventoryHSQLDB deckIDb;
    @Before
    public void setup() {
        deckIDb = new DeckInventoryHSQLDB("jdbc:hsqldb:hsql://localhost/");
    }

    @Test
    public void testCreateDeck(){
        IDeck deck = deckIDb.createDeck("TestDeck");
        DeckDetails info = deck.getInfo();
        assertEquals(info.getId(), 0);
        assertEquals(info.getName(), "TestDeck");
    }

    @Test
    public void testGetDeck(){
        DeckDetails newDeck = deckIDb.createDeck("TestDeck").getInfo();
        IDeck deck = deckIDb.getDeck(newDeck);
        DeckDetails info = deck.getInfo();
        assertEquals(info.getId(), 0);
        assertEquals(info.getName(), "TestDeck");
    }

    @Test
    public void testGetNXDeck(){
        assertNull(deckIDb.getDeck(new DeckDetails(-1,"")));
    }

    @Test
    public void testDeleteDeck() {
        // Create an instance of DeckInventoryStub
        DeckInventoryHSQLDB deckInventoryStub = new DeckInventoryHSQLDB("jdbc:hsqldb:hsql://localhost/");
        IDeck sampleDeck = deckInventoryStub.createDeck("Test Deck");
        DeckDetails deckDetails = sampleDeck.getInfo();
        assertNotNull(deckInventoryStub.getDeck(deckDetails));
        deckInventoryStub.deleteDeck(deckDetails);
        assertNull(deckInventoryStub.getDeck(deckDetails));
    }
}
