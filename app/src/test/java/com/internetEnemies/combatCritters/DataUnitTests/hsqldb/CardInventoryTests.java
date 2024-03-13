package com.internetEnemies.combatCritters.DataUnitTests.hsqldb;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardInventoryTests {

    static Card[] cards = {
            new ItemCard(1,"","",1, Card.Rarity.RARE,1),
            new ItemCard(2,"","",1, Card.Rarity.RARE,1),
            new ItemCard(3,"","",1, Card.Rarity.RARE,1),
            new ItemCard(4,"","",1, Card.Rarity.RARE,1)
    };
    ICardInventory cardDB;


    @Before
    public void setup () throws IOException {
        String path = TestUtils.getDBPath();
        cardDB = new CardInventoryHSQLDB(path);
        RegistryCardHSQLDB cardRegistry = new RegistryCardHSQLDB(path);

        cards = new Card[]{
                cardRegistry.addCard(new ItemCard(1, "", "", 1, Card.Rarity.RARE, 1)),
                cardRegistry.addCard(new ItemCard(1, "", "", 1, Card.Rarity.RARE, 1)),
                cardRegistry.addCard(new ItemCard(1, "", "", 1, Card.Rarity.RARE, 1)),
                cardRegistry.addCard(new ItemCard(1, "", "", 1, Card.Rarity.RARE, 1))
        };
    }

    @Test
    public void testDbAddRemove() {
        assertEquals(cardDB.getCardAmount(cards[0]),0);
        cardDB.addCard(cards[0]);
        assertEquals(cardDB.getCardAmount(cards[0]),1);
        cardDB.removeCard(cards[0]);
        assertEquals(cardDB.getCardAmount(cards[0]),0);
    }

    @Test
    public void testDbAddRemoveMultiple() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(cards[0]);
        cardList.add(cards[0]);
        cardList.add(cards[0]);
        cardList.add(cards[1]);

        cardDB.addCards(cardList);
        assertEquals(cardDB.getCardAmount(cards[0]),3);
        assertEquals(cardDB.getCardAmount(cards[1]),1);
        cardDB.removeCard(cards[0],3);
        assertEquals(cardDB.getCardAmount(cards[0]),0);
    }
}
