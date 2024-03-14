package com.internetEnemies.combatCritters.IntegrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DeckManagerTest {

    private IDeckManager deckManager;

    private IDeckInventory deckInventory;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        deckInventory = new DeckInventoryHSQLDB(path);
        deckManager = new DeckManager(deckInventory);
    }

    @Test
    public void testNullConstructor() {
        deckManager = new DeckManager();
        assertNotNull(deckManager);
        assertNotNull(deckManager.createDeck("test"));
    }

    @Test
    public void testCreateDeck() {
        DeckDetails test1Info = deckManager.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals("test1", test1Info.getName());
        assertEquals(1, test1Info.getId());
        assertEquals(test1Info,deckInventory.getDeck(test1Info).getInfo());
    }

    @Test
    public void testCreateMultipleDecks() {
        DeckDetails test1Info = deckManager.createDeck("test1");
        DeckDetails test2Info = deckManager.createDeck("test2");
        DeckDetails test3Info = deckManager.createDeck("test3");
        assertNotNull(test1Info);
        assertNotNull(test2Info);
        assertNotNull(test3Info);
        assertEquals("test1", test1Info.getName());
        assertEquals("test2", test2Info.getName());
        assertEquals("test3", test3Info.getName());
        assertEquals(1, test1Info.getId());
        assertEquals(2, test2Info.getId());
        assertEquals(3, test3Info.getId());
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        assertEquals(deckInventory.getDeck(test2Info).getInfo(),test2Info);
        assertEquals(deckInventory.getDeck(test3Info).getInfo(),test3Info);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDeckFailure() {
        deckManager.createDeck("");
    }

    @Test
    public void testDeleteDeck() {
        DeckDetails test1Info = deckManager.createDeck("test1");
        deckManager.deleteDeck(test1Info);
        test1Info = deckManager.createDeck("test1");
        deckManager.deleteDeck(test1Info);
    }

    @Test
    public void testDeleteMultipleDecks() {
        DeckDetails test1Info = deckManager.createDeck("test1");
        DeckDetails test2Info = deckManager.createDeck("test2");
        DeckDetails test3Info = deckManager.createDeck("test3");
        assertNotNull(test1Info);
        assertNotNull(test2Info);
        assertNotNull(test3Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        assertEquals(deckInventory.getDeck(test2Info).getInfo(),test2Info);
        assertEquals(deckInventory.getDeck(test3Info).getInfo(),test3Info);
        deckManager.deleteDeck(test1Info);
        deckManager.deleteDeck(test2Info);
        deckManager.deleteDeck(test3Info);
    }

    @Test
    public void testDeleteDeckFailure() {
        DeckDetails test1Info = deckManager.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        deckManager.deleteDeck(test1Info);
        deckManager.deleteDeck(test1Info);
        test1Info = deckManager.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        deckManager.deleteDeck(test1Info);
    }

    @Test (expected = AssertionError.class)
    public void testDeleteDeckNull(){
        deckManager.deleteDeck(null);
    }

    @Test
    public void testGetBuilder(){
        DeckDetails test1Info = deckManager.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        IDeckBuilder builder = deckManager.getBuilder(test1Info);
        assertNotNull(builder);
        assertEquals(0,builder.getTotalNumOfCards());
    }

    @Test (expected = AssertionError.class)
    public void testGetBuilderFailure(){
        DeckDetails test1Info = deckManager.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        deckManager.deleteDeck(test1Info);
        deckManager.getBuilder(test1Info);
    }

    @Test (expected = AssertionError.class)
    public void testGetBuilderNullFailure(){
        deckManager.getBuilder(null);
    }

    @Test
    public void testGetDecks() {
        DeckDetails test1Info = deckManager.createDeck("test1");
        DeckDetails test2Info = deckManager.createDeck("test2");
        DeckDetails test3Info = deckManager.createDeck("test3");
        assertNotNull(test1Info);
        assertNotNull(test2Info);
        assertNotNull(test3Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        assertEquals(deckInventory.getDeck(test2Info).getInfo(),test2Info);
        assertEquals(deckInventory.getDeck(test3Info).getInfo(),test3Info);
        assertTrue(deckManager.getDecks().contains(test1Info));
        assertTrue(deckManager.getDecks().contains(test2Info));
        assertTrue(deckManager.getDecks().contains(test3Info));
    }

    @Test
    public void testGetDeletedDecks() {
        DeckDetails test1Info = deckManager.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        assertTrue(deckManager.getDecks().contains(test1Info));
        deckManager.deleteDeck(test1Info);
        assertFalse(deckManager.getDecks().contains(test1Info));
    }

    @Test
    public void testContains(){
        DeckDetails test1Info = deckManager.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
    }

    @Test
    public void testContainsMultipleDecks(){
        DeckDetails test1Info = deckManager.createDeck("test1");
        assertNotNull(test1Info);
        assertEquals(deckInventory.getDeck(test1Info).getInfo(),test1Info);
        DeckDetails test2Info = deckManager.createDeck("test2");
        assertNotNull(test2Info);
        assertEquals(deckInventory.getDeck(test2Info).getInfo(),test2Info);
        DeckDetails test3Info = deckManager.createDeck("test3");
        assertNotNull(test3Info);
        assertEquals(deckInventory.getDeck(test3Info).getInfo(),test3Info);
        assertNotNull(deckInventory.getDeck(test1Info));
        assertNotNull(deckInventory.getDeck(test2Info));
        assertNotNull(deckInventory.getDeck(test3Info));
    }

}