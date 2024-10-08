package com.internetEnemies.combatCritters.hsqldb;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.exception.NXDeckException;
import com.internetEnemies.combatCritters.data.hsqldb.DeckHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;

import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DeckTest {
    IDeck deckDB;
    RegistryCardHSQLDB cardsRegistry;
    //Cards for testing
    Card card1;
    Card card2;
    @Before
    public void setup() throws IOException, NXDeckException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        cardsRegistry = new RegistryCardHSQLDB(path);
        card1 = cardsRegistry.addCard(new ItemCard(-1,"","",0, Card.Rarity.RARE,0));
        card2 = cardsRegistry.addCard(new ItemCard(-1,"","",0, Card.Rarity.RARE,0));
        deckDB = new DeckInventoryHSQLDB(path, dummy).createDeck("TestDeck");
    }

    //todo finish these tests

    @Test
    public void testAdd() {
        deckDB.addCard(0,card1);
        assertEquals(card1, deckDB.getCard(0));
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
