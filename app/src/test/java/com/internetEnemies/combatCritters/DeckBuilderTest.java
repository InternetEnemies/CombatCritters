/**
 * DeckBuilderTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     02-February-2024
 *
 * PURPOSE:     Unit Test for DeckBuilder
 */

package com.internetEnemies.combatCritters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class DeckBuilderTest {
    private IDeckBuilder deckBuilder;

    private final static Card[] cards = {//TODO these should fetch from the registry instead (maybe)
            new ItemCard(1,"","",1, Card.Rarity.RARE,1),
            new ItemCard(2,"","",1, Card.Rarity.RARE,1),
            new ItemCard(3,"","",1, Card.Rarity.RARE,1),
            new ItemCard(4,"","",1, Card.Rarity.RARE,1)
    };

    /**
     * THIS IS NOT AN ACTUAL LIST, TESTING ONLY METHOD
     * use cautiously, this just an instance of the decks we have, only used for testing
     * @return A list of current IDeck object
     */
    private ArrayList<IDeck> list (){
        try{
            Iterator<IDeck> listIterator = deckBuilder.iterator();
            ArrayList<IDeck> list = new ArrayList<>();
            while (listIterator.hasNext()) {
                list.add(listIterator.next());
            }
            return list;
        }catch(Exception x){
            return null;
        }
    }
    @Before
    public void setup(){ deckBuilder = new DeckBuilder(); }

    @Test
    public void testDeckBuilderConstructor(){
        assert(deckBuilder instanceof DeckBuilder);
    }

    @Test
    public void testList(){
        assert(Objects.requireNonNull(list()).isEmpty());
        assert(deckBuilder.createNewDeck("testDeck1"));
        assert(deckBuilder.createNewDeck("testDeck2"));
        assert(deckBuilder.createNewDeck("testDeck3"));
        ArrayList<IDeck> list = list();
        assert list != null;
        assertEquals(3,list.size());
    }

    @Test
    public void testCreateNewDeck(){
        ArrayList<IDeck> list = list();
        assert list != null;
        assertEquals(0,list.size());
        assert(deckBuilder.createNewDeck("TestDeck1"));
        list = list();
        assertEquals(1, Objects.requireNonNull(list()).size());
        assert list != null;
        assertEquals("TestDeck1",list.get(0).getInfo().getName());
        assert(deckBuilder.createNewDeck("TestDeck2"));
        assert(deckBuilder.createNewDeck("TestDeck3"));
        list = list();
        assert list != null;
        assertEquals("TestDeck1",list.get(0).getInfo().getName());
        assertEquals("TestDeck2",list.get(1).getInfo().getName());
        assertEquals("TestDeck3",list.get(2).getInfo().getName());
    }

    @Test
    public void testAddCard(){
        ArrayList<IDeck> list;
        assert(deckBuilder.createNewDeck("TestDeck1"));
        list = list();
        assert list != null;
        IDeck theDeck = list.get(0);
        Card card = cards[0];
        assert(deckBuilder.addCard(card,theDeck));
        list = list();
        assert list != null;
        theDeck = list.get(0);
        assertEquals(1,theDeck.countCard(card));
        assert(deckBuilder.addCard(card,theDeck));
        assert(deckBuilder.addCard(card,theDeck));
        list = list();
        assert list != null;
        theDeck = list.get(0);
        assertEquals(3,theDeck.countCard(card));
        card = cards[1];
        assert(deckBuilder.addCard(card,theDeck));
        assertEquals(1,theDeck.countCard(card));
        card = cards[0];
        assertEquals(3,theDeck.countCard(card));
        assert(deckBuilder.addCard(card,theDeck));
        assertEquals(4,theDeck.countCard(card));
    }

    @Test
    public void testRemoveSingleCard(){
        ArrayList<IDeck> list;
        deckBuilder.createNewDeck("TestDeck1");
        list = list();
        assert list != null;
        IDeck theDeck = list.get(0);
        assert(deckBuilder.addCard(cards[0],theDeck));
        assertEquals(1,theDeck.countCard(cards[0]));
        assert(deckBuilder.removeCard(cards[0],theDeck));
        assertEquals(0,theDeck.countCard(cards[0]));
    }

    @Test
    public void testRemoveMultipleCards(){
        ArrayList<IDeck> list;
        deckBuilder.createNewDeck("TestDeck1");
        list = list();
        assert list != null;
        IDeck theDeck = list.get(0);
        assert(deckBuilder.addCard(cards[0],theDeck));
        assert(deckBuilder.addCard(cards[0],theDeck));
        assert(deckBuilder.addCard(cards[0],theDeck));
        assertEquals(3,theDeck.countCard(cards[0]));
        assert(deckBuilder.removeCard(cards[0],theDeck));
        assert(deckBuilder.removeCard(cards[0],theDeck));
        assert(deckBuilder.removeCard(cards[0],theDeck));
        assertEquals(0,theDeck.countCard(cards[0]));
    }

    @Test
    public void testRemoveMultipleTypesCard(){
        ArrayList<IDeck> list;
        deckBuilder.createNewDeck("TestDeck1");
        list = list();
        assert list != null;
        IDeck theDeck = list.get(0);
        //testing case: multiple types of card
        assert(deckBuilder.addCard(cards[0],theDeck));
        assert(deckBuilder.addCard(cards[0],theDeck));
        assert(deckBuilder.addCard(cards[1],theDeck));
        assert(deckBuilder.removeCard(cards[0],theDeck));
        assertEquals(1,theDeck.countCard(cards[0]));
    }

    @Test
    public void testFailRemoveCard(){
        ArrayList<IDeck> list;
        deckBuilder.createNewDeck("TestDeck1");
        list = list();
        assert list != null;
        IDeck theDeck = list.get(0);
        //testing case: multiple types of card
        assert(deckBuilder.addCard(cards[0],theDeck));
        assert(deckBuilder.addCard(cards[0],theDeck));
        assert(deckBuilder.removeCard(cards[0],theDeck));
        assert(deckBuilder.removeCard(cards[0],theDeck));
        assertFalse(deckBuilder.removeCard(cards[0],theDeck));
        assert(deckBuilder.addCard(cards[0],theDeck));
        assertFalse(deckBuilder.removeCard(cards[1],theDeck));
    }
}
