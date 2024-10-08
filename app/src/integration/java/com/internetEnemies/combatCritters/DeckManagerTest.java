package com.internetEnemies.combatCritters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.internetEnemies.combatCritters.Logic.inventory.decks.DeckManager;
import com.internetEnemies.combatCritters.Logic.inventory.decks.DeckValidator;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckBuilder;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckManager;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IDeckInventory;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeckManagerTest {

    private IDeckManager deckManager;

    private IDeckInventory deckInventory;
    private ICardInventory cardInventory;
    RegistryCardHSQLDB cards;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        cards = new RegistryCardHSQLDB(path);
        deckInventory = new DeckInventoryHSQLDB(path,dummy);
        cardInventory = new CardInventoryHSQLDB(path, dummy);
        deckManager = new DeckManager(deckInventory, cardInventory, new DeckValidator(cardInventory));
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

    @Test
    public void testInvalidGetDeck() {
        addInvalidDeckCardsToInventory();
        DeckDetails deck = deckManager.createDeck("test");
        addInventoryCardsToDeck(deckManager.getBuilder(deck));
        assertEquals(deckManager.getValidDecks().size(), 0);
    }

    @Test
    public void testValidGetDeck() {
        addValidDeckCardsToInventory();
        DeckDetails deck = deckManager.createDeck("test");
        addInventoryCardsToDeck(deckManager.getBuilder(deck));
        assertEquals(deckManager.getValidDecks().size(), 1);
    }

    private void addValidDeckCardsToInventory() {
        List<Card> cardsList = new ArrayList<>();
        Card card = cards.addCard(new CritterCard(0, "", "", 0, Card.Rarity.COMMON, 0, 0, null));
        for(int i = 0; i < 25; i++) {
            cardsList.add(card);
        }
        cardInventory.addCards(cardsList);
    }

    private void addInvalidDeckCardsToInventory() {
        List<Card> cardsList = new ArrayList<>();
        Card card = cards.addCard(new CritterCard(0, "", "", 0, Card.Rarity.COMMON, 0, 0, null));
        for(int i = 0; i < 15; i++) {
            cardsList.add(card);
        }
        cardInventory.addCards(cardsList);
    }

    private void addInventoryCardsToDeck(IDeckBuilder builder) {
        List<ItemStack<Card>> cardsList = cardInventory.getCards();
        for(ItemStack<Card> cardStack : cardsList) {
            for(int i = 0; i < cardStack.getAmount(); i++) {
                builder.addCard(cardStack.getItem());
            }
        }
    }
    
    @Test
    public void test_getDeckFromId(){
        DeckDetails deck = this.deckManager.createDeck("test");
        assertEquals(deck, this.deckManager.getDeckDetails(deck.getId()));
    }
}
