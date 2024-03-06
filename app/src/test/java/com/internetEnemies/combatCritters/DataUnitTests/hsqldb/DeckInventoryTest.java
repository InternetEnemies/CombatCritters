package com.internetEnemies.combatCritters.DataUnitTests.hsqldb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.DeckInventoryStub;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DeckInventoryTest {
    IDeckInventory deckIDb;
    @Before
    public void setup() throws IOException {
        File db = TestUtils.copyDB();
        deckIDb = new DeckInventoryHSQLDB(db.getAbsolutePath().replace(".script", ""));
    }

    @Test
    public void testCreateDeck(){
        IDeck deck = deckIDb.createDeck("TestDeck");
        DeckDetails info = deck.getInfo();
        assertNotNull(info);
        assertEquals(1, info.getId());
        assertEquals("TestDeck", info.getName());
    }

    @Test
    public void testGetDeck(){
        DeckDetails newDeck = deckIDb.createDeck("TestDeck").getInfo();
        IDeck deck = deckIDb.getDeck(newDeck);
        DeckDetails info = deck.getInfo();
        assertEquals(1, info.getId());
        assertEquals("TestDeck", info.getName());
    }

    @Test
    public void testGetNXDeck(){
        assertNull(deckIDb.getDeck(new DeckDetails(-1,"")));
    }

    @Test
    public void testDeleteDeck() {
        // Create an instance of DeckInventoryStub
        DeckInventoryStub deckInventoryStub = new DeckInventoryStub();
        IDeck sampleDeck = deckInventoryStub.createDeck("Test Deck");
        DeckDetails deckDetails = sampleDeck.getInfo();
        assertNotNull(deckInventoryStub.getDeck(deckDetails));
        deckInventoryStub.deleteDeck(deckDetails);
        assertNull(deckInventoryStub.getDeck(deckDetails));
    }
}
