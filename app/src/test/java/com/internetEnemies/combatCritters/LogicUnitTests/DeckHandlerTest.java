package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.DeckHandler;
import com.internetEnemies.combatCritters.Logic.IDeckHandler;
import com.internetEnemies.combatCritters.objects.DeckDetails;

public class DeckHandlerTest {

    private IDeckHandler deckHandler;

    @Before
    public void setup(){
        deckHandler = new DeckHandler();
    }

    @Test
    public void testConstructor(){
        assertNotNull(deckHandler);
        assertTrue(deckHandler instanceof DeckHandler);
    }

    @Test
    public void testCreateDeck(){
        DeckDetails test1Info = deckHandler.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals("test1",test1Info.getName());
        assertEquals(0,test1Info.getId());
        assertEquals(deckHandler.getInventory().getDeck(0).getInfo(),test1Info);
    }

    @Test
    public void testCreateMultipleDecks(){
        DeckDetails test1Info = deckHandler.createDeck("test1");
        DeckDetails test2Info = deckHandler.createDeck("test2");
        DeckDetails test3Info = deckHandler.createDeck("test3");
        assertNotNull(test1Info);
        assertNotNull(test2Info);
        assertNotNull(test3Info);
        assertEquals("test1",test1Info.getName());
        assertEquals("test2",test2Info.getName());
        assertEquals("test3",test3Info.getName());
        assertEquals(0,test1Info.getId());
        assertEquals(1,test2Info.getId());
        assertEquals(2,test3Info.getId());
        assertEquals(deckHandler.getInventory().getDeck(0).getInfo(),test1Info);
        assertEquals(deckHandler.getInventory().getDeck(1).getInfo(),test2Info);
        assertEquals(deckHandler.getInventory().getDeck(2).getInfo(),test3Info);
    }

    @Test
    public void testCreateDeckFailure(){
        assertNull(deckHandler.createDeck(null));
    }

    @Test
    public void testDeleteDeck(){
        DeckDetails test1Info = deckHandler.createDeck("test1");
        assertTrue(deckHandler.deleteDeck(test1Info));
        test1Info = deckHandler.createDeck("test1");
        assertTrue(deckHandler.deleteDeck(test1Info));
    }

    @Test
    public void testDeleteMultipleDecks(){
        DeckDetails test1Info = deckHandler.createDeck("test1");
        DeckDetails test2Info = deckHandler.createDeck("test2");
        DeckDetails test3Info = deckHandler.createDeck("test3");
        assertNotNull(test1Info);
        assertNotNull(test2Info);
        assertNotNull(test3Info);
        assertEquals(deckHandler.getInventory().getDeck(0).getInfo(),test1Info);
        assertEquals(deckHandler.getInventory().getDeck(1).getInfo(),test2Info);
        assertEquals(deckHandler.getInventory().getDeck(2).getInfo(),test3Info);
        assertTrue(deckHandler.deleteDeck(test1Info));
        assertTrue(deckHandler.deleteDeck(test2Info));
        assertTrue(deckHandler.deleteDeck(test3Info));
    }

    @Test
    public void testDeleteDeckFailure(){
        DeckDetails test1Info = deckHandler.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckHandler.getInventory().getDeck(0).getInfo(),test1Info);
        assertTrue(deckHandler.deleteDeck(test1Info));
        assertFalse(deckHandler.deleteDeck(test1Info));
        test1Info = deckHandler.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckHandler.getInventory().getDeck(0).getInfo(),test1Info);
        assertFalse(deckHandler.deleteDeck(null));
        assertTrue(deckHandler.deleteDeck(test1Info));
    }
}
