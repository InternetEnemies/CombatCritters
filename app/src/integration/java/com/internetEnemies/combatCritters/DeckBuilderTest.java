/**
 * DeckBuilderTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     02-February-2024
 *
 * PURPOSE:     Unit Test for DeckBuilder
 */

package com.internetEnemies.combatCritters;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.Logic.inventory.decks.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckBuilder;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;

import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class DeckBuilderTest {
    private IDeckBuilder deckBuilder;
    private IDeck deck;

    private Card[] cards;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        RegistryCardHSQLDB cardDB = new RegistryCardHSQLDB(path);
        cards = new Card[]{
                cardDB.addCard(new ItemCard(-1,"","",1, Card.Rarity.RARE,1)),
                cardDB.addCard(new ItemCard(-1,"","",1, Card.Rarity.RARE,1)),
                cardDB.addCard(new ItemCard(-1,"","",1, Card.Rarity.RARE,1)),
                cardDB.addCard(new ItemCard(-1,"","",1, Card.Rarity.RARE,1))
        };
        deck = new DeckInventoryHSQLDB(path,dummy).createDeck("TestDeck");

        deckBuilder = new DeckBuilder(deck);
    }

    @Test
    public void testDeckBuilderConstructor(){
        assert deckBuilder != null;
    }

    @Test
    public void testAddCard(){
        deckBuilder.addCard(cards[0]);
        assertEquals(1,deck.countCard(cards[0]));
    }

    @Test
    public void testAddMultipleCards(){
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[0]);
        assertEquals(2,deck.countCard(cards[0]));
    }

    @Test
    public void testAddMultipleTypesCard(){
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[1]);
        deckBuilder.addCard(cards[2]);
        assertEquals(2,deck.countCard(cards[0]));
        assertEquals(1,deck.countCard(cards[1]));
        assertEquals(1,deck.countCard(cards[2]));
    }

    @Test(expected = AssertionError.class)
    public void testAddCardFailure(){
        deckBuilder.addCard(null);
    }

    @Test
    public void testRemoveCard(){
        deckBuilder.addCard(cards[0]);
        assertEquals(1,deck.countCard(cards[0]));
        deckBuilder.removeCard(0);
    }

    @Test
    public void testRemoveMultipleCards(){
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[0]);
        assertEquals(4,deck.countCard(cards[0]));
        deckBuilder.removeCard(0);
        deckBuilder.removeCard(0);
        deckBuilder.removeCard(0);
        deckBuilder.removeCard(0);
        assertEquals(0,deck.countCard(cards[0]));
    }

    @Test
    public void testRemoveMultipleTypesCard(){
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[1]);
        deckBuilder.addCard(cards[2]);
        deckBuilder.addCard(cards[3]);
        assertEquals(1,deck.countCard(cards[0]));
        assertEquals(1,deck.countCard(cards[1]));
        assertEquals(1,deck.countCard(cards[2]));
        assertEquals(1,deck.countCard(cards[3]));
        deckBuilder.removeCard(0);
        deckBuilder.removeCard(0);
        deckBuilder.removeCard(0);
        deckBuilder.removeCard(0);
        assertEquals(0,deck.countCard(cards[0]));
        assertEquals(0,deck.countCard(cards[1]));
        assertEquals(0,deck.countCard(cards[2]));
        assertEquals(0,deck.countCard(cards[3]));
    }

    @Test
    public void testGetCards(){
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[1]);
        List<Card> deck = deckBuilder.getCards();
        assertEquals(deck.get(0), cards[0]);
        assertEquals(deck.get(1), cards[0]);
        assertEquals(deck.get(2), cards[0]);
        assertEquals(deck.get(3), cards[1]);
    }

    @Test
    public void testGetTotalNumOfCards(){
        assertEquals(0,deckBuilder.getTotalNumOfCards());
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[1]);
        deckBuilder.addCard(cards[2]);
        deckBuilder.addCard(cards[3]);
        assertEquals(4,deckBuilder.getTotalNumOfCards());
    }

    @Test
    public void testGetNumOfCard(){
        assertEquals(0, deckBuilder.getNumOfCard(cards[0]));
        deckBuilder.addCard(cards[0]);
        assertEquals(1, deckBuilder.getNumOfCard(cards[0]));
        deckBuilder.addCard(cards[0]);
        deckBuilder.addCard(cards[0]);
        assertEquals(3, deckBuilder.getNumOfCard(cards[0]));
        deckBuilder.removeCard(0);
        assertEquals(2, deckBuilder.getNumOfCard(cards[0]));
        deckBuilder.addCard(cards[2]);
        deckBuilder.addCard(cards[2]);
        assertEquals(2, deckBuilder.getNumOfCard(cards[2]));
    }
}
