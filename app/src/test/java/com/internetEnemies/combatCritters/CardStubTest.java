package com.internetEnemies.combatCritters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import com.internetEnemies.combatCritters.data.CardsStub;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PacksStub;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.Card;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;

public class CardStubTest {
    private IRegistry<Card> cardDB;

    static Card[] cards = {//TODO these should fetch from the registry instead (maybe)
            new ItemCard(1,"","",1, Card.Rarity.RARE,1),
            new ItemCard(2,"","",1, Card.Rarity.RARE,1),
            new ItemCard(3,"","",1, Card.Rarity.RARE,1),
            new ItemCard(4,"","",1, Card.Rarity.RARE,1)
    };



    public void setup(){

        Map<Integer, Card> cardList = new TreeMap<>();
        cardList.put(cards[0].getId(), cards[0]);
        cardList.put(cards[1].getId(), cards[1]);
        cardList.put(cards[2].getId(), cards[2]);
        cardList.put(cards[3].getId(), cards[3]);

        cardDB = new CardsStub(cardList);

    }
    @Test
    public void testGetSingle(){
        setup();
        Card single = cardDB.getSingle(1);
        assertEquals(single, cards[0]);

        single = cardDB.getSingle(2);
        assertEquals(single, cards[1]);

        single = cardDB.getSingle(-1);
        assertNull(single);

    }

    @Test
    public void testGetListOf(){
        setup();
        List<Card> comparison = new ArrayList<>();
        comparison.add(cards[0]);
        comparison.add(cards[2]);


        List<Integer> getCards = new ArrayList<>();
        getCards.add(1);
        getCards.add(3);


        List<Card> resultSet = cardDB.getListOf(getCards);
        assertEquals(comparison, resultSet);

    }

    @Test
    public void testIterator() {
        // Create a sample map of cards
        Map<Integer, Card> sampleCards = new HashMap<>();
        sampleCards.put(1, new ItemCard(1,"","",1, Card.Rarity.RARE,1));
        sampleCards.put(2, new ItemCard(2,"","",1, Card.Rarity.RARE,1));
        sampleCards.put(3, new ItemCard(3,"","",1, Card.Rarity.RARE,1));

        // Create an instance of CardsStub with the sample map
        CardsStub cardsStub = new CardsStub(sampleCards);

        // Get the iterator from CardsStub
        Iterator<Card> iterator = cardsStub.iterator();

        // Check if the iterator has the correct number of elements
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}
