package com.internetEnemies.combatCritters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.data.DeckInventoryStub;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.DeckDetails;

public class DeckInventoryStubTest {
    DeckInventoryStub deckIDb;
    @Before
    public void setup() {
        deckIDb = new DeckInventoryStub();
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
        deckIDb.createDeck("TestDeck");
        IDeck deck = deckIDb.getDeck(0);
        DeckDetails info = deck.getInfo();
        assertEquals(info.getId(), 0);
        assertEquals(info.getName(), "TestDeck");
    }

    @Test
    public void testGetNXDeck(){
        assertNull(deckIDb.getDeck(0));
    }
}
