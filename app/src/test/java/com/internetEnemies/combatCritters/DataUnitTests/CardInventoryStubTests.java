package com.internetEnemies.combatCritters.DataUnitTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.ArrayList;
import java.util.List;

public class CardInventoryStubTests {

    static Card[] cards = {
            new ItemCard(1,"","",1, Card.Rarity.RARE,1),
            new ItemCard(2,"","",1, Card.Rarity.RARE,1),
            new ItemCard(3,"","",1, Card.Rarity.RARE,1),
            new ItemCard(4,"","",1, Card.Rarity.RARE,1)
    };
    ICardInventory cardDB;


    @Before
    public void setup () {
        cardDB = new CardInventoryStub();
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
