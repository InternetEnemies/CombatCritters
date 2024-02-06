/**
 * DeckBuilderTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     02-February-2024
 *
 * PURPOSE:     Unit Test for DeckBuilder
 */

package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.data.DeckStub;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;


public class DeckBuilderTest {
    private DeckBuilder deckBuilder;
    private IDeck deck;

    private final static Card[] cards = {//TODO these should fetch from the registry instead (maybe)
            new ItemCard(1,"","",1, Card.Rarity.RARE,1),
            new ItemCard(2,"","",1, Card.Rarity.RARE,1),
            new ItemCard(3,"","",1, Card.Rarity.RARE,1),
            new ItemCard(4,"","",1, Card.Rarity.RARE,1)
    };

    @Before
    public void setup(){
        deck = new DeckStub(new DeckDetails(1,"TestDeck"));
        deckBuilder = new DeckBuilder(deck);
    }

    @Test
    public void testDeckBuilderConstructor(){
        assert(deckBuilder instanceof DeckBuilder);
    }

    @Test
    public void testSearchingInventory(){
        deckInventory.createDeck("NotTest");
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        //true cases
        assertTrue(deckBuilder.addCard(cards[0],deckInfo));
        assertTrue(deckBuilder.removeCard(cards[0],deckInfo));
        //false cases
        deckInventory.deleteDeck(deckInfo.getId());
        assertFalse(deckBuilder.addCard(cards[0],deckInfo));
        assertFalse(deckBuilder.removeCard(cards[0],deckInfo));
    }

    @Test
    public void testAddCard(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        deckBuilder.addCard(cards[0],deckInfo);
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[0]));
    }

    @Test
    public void testAddMultipleCards(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        deckBuilder.addCard(cards[0],deckInfo);
        deckBuilder.addCard(cards[0],deckInfo);
        assertEquals(2,deckInventory.getDeck(0).countCard(cards[0]));
    }

    @Test
    public void testAddMultipleTypesCard(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        deckBuilder.addCard(cards[0],deckInfo);
        deckBuilder.addCard(cards[0],deckInfo);
        deckBuilder.addCard(cards[1],deckInfo);
        deckBuilder.addCard(cards[2],deckInfo);
        assertEquals(2,deckInventory.getDeck(0).countCard(cards[0]));
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[1]));
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[2]));
    }

    @Test
    public void testAddCardFailure(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        assertFalse(deckBuilder.addCard(null,deckInfo));
        assertFalse(deckBuilder.addCard(cards[1],null));
    }

    @Test
    public void testRemoveCard(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        deckBuilder.addCard(cards[0],deckInfo);
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[0]));
        assertTrue(deckBuilder.removeCard(cards[0],deckInfo));
    }

    @Test
    public void testRemoveMultipleCards(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        deckBuilder.addCard(cards[0],deckInfo);
        deckBuilder.addCard(cards[0],deckInfo);
        deckBuilder.addCard(cards[0],deckInfo);
        deckBuilder.addCard(cards[0],deckInfo);
        assertEquals(4,deckInventory.getDeck(0).countCard(cards[0]));
        assertTrue(deckBuilder.removeCard(cards[0],deckInfo));
        assertTrue(deckBuilder.removeCard(cards[0],deckInfo));
        assertTrue(deckBuilder.removeCard(cards[0],deckInfo));
        assertTrue(deckBuilder.removeCard(cards[0],deckInfo));
        assertEquals(0,deckInventory.getDeck(0).countCard(cards[0]));
    }

    @Test
    public void testRemoveMultipleTypesCard(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        deckBuilder.addCard(cards[0],deckInfo);
        deckBuilder.addCard(cards[1],deckInfo);
        deckBuilder.addCard(cards[2],deckInfo);
        deckBuilder.addCard(cards[3],deckInfo);
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[0]));
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[1]));
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[2]));
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[3]));
        assertTrue(deckBuilder.removeCard(cards[0],deckInfo));
        assertTrue(deckBuilder.removeCard(cards[1],deckInfo));
        assertTrue(deckBuilder.removeCard(cards[2],deckInfo));
        assertTrue(deckBuilder.removeCard(cards[3],deckInfo));
        assertEquals(0,deckInventory.getDeck(0).countCard(cards[0]));
        assertEquals(0,deckInventory.getDeck(0).countCard(cards[1]));
        assertEquals(0,deckInventory.getDeck(0).countCard(cards[2]));
        assertEquals(0,deckInventory.getDeck(0).countCard(cards[3]));
    }

    @Test
    public void testRemoveCardFailure(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        deckBuilder.addCard(cards[0],deckInfo);
        assertEquals(1,deckInventory.getDeck(0).countCard(cards[0]));
        assertTrue(deckBuilder.removeCard(cards[0],deckInfo));
        assertEquals(0,deckInventory.getDeck(0).countCard(cards[0]));
        assertFalse(deckBuilder.removeCard(cards[0],deckInfo));
        assertFalse(deckBuilder.removeCard(null,deckInfo));
        assertFalse(deckBuilder.removeCard(cards[0],null));
    }

    @Test
    public void testGetCards(){
        IDeck testDeck= deckInventory.createDeck("Test");
        DeckDetails deckInfo = testDeck.getInfo();
        deckBuilder.addCard(cards[0],deckInfo);
        deckBuilder.addCard(cards[1],deckInfo);
        deckBuilder.addCard(cards[2],deckInfo);
        deckBuilder.addCard(cards[3],deckInfo);
        assertEquals(testDeck.countCards(),deckBuilder.getCards(deckInfo));
    }
}
