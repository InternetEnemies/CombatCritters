package com.internetEnemies.combatCritters.DataUnitTests.hsqldb;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.data.DeckStub;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;

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

        assertEquals(3,deckDB.countCard(card1));
        assertEquals(1,deckDB.countCard(card2));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNXSlot(){
        deckDB.getCard(1);
    }
}
