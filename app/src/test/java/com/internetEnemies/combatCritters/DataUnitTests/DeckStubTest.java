package com.internetEnemies.combatCritters.DataUnitTests;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.data.DeckStub;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class DeckStubTest {
    DeckStub deckDB;
    //Cards for testing
    Card card1 = new ItemCard(0,"","",0, Card.Rarity.RARE,0);
    Card card2 = new ItemCard(1,"","",0, Card.Rarity.RARE,0);
    @Before
    public void setup() {
        deckDB = new DeckStub(new DeckDetails(1,"TestDeck"));

    }


    @Test
    public void testAdd() {
        deckDB.addCard(0,card1);
        assertEquals(deckDB.getCard(0), card1);
    }

    @Test
    public void testCountCard(){
        assertEquals(deckDB.countCard(card1),0);

        deckDB.addCard(0,card1);
        deckDB.addCard(0,card1);
        deckDB.addCard(0,card1);
        deckDB.addCard(0,card2);

        assertEquals(deckDB.countCard(card1),3);
        assertEquals(deckDB.countCard(card2),1);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNXSlot(){
        deckDB.getCard(1);
    }

    @Test
    public void testIterator() {
        // Create a sample deck with some cards
        DeckDetails deckDetails = new DeckDetails(1, "Test Deck");
        DeckStub deckStub = new DeckStub(deckDetails);

        Card commonCard = new ItemCard(1, "Common Card", null, 0, Card.Rarity.COMMON, 1);
        Card uncommonCard = new ItemCard(2, "Uncommon Card", null, 0, Card.Rarity.UNCOMMON, 1);
        Card rareCard = new ItemCard(3, "Rare Card", null, 0, Card.Rarity.RARE, 1);

        // Add cards to the deck
        deckStub.addCard(0, commonCard);
        deckStub.addCard(1, uncommonCard);
        deckStub.addCard(2, rareCard);

        // Get the iterator
        Iterator<Card> iterator = deckStub.iterator();

        // Verify the order and presence of cards
        assertEquals(commonCard, iterator.next());
        assertEquals(uncommonCard, iterator.next());
        assertEquals(rareCard, iterator.next());

        // Ensure there are no more cards
        assertFalse(iterator.hasNext());
    }
}
