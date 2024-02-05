/**
 * DeckMangerTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     05-February-2024
 *
 * PURPOSE:     Unit Test for DeckManager, since DeckBuilder and DeckHandler are protected classes,
 *              this unit test will be an integrated test for these three classes
 */

package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;

public class DeckManagerTest {
    private IDeckManager deckManager;

    private final static Card[] cards = {
            new ItemCard(1,"","",1, Card.Rarity.RARE,1),
            new ItemCard(2,"","",1, Card.Rarity.RARE,1),
            new ItemCard(3,"","",1, Card.Rarity.RARE,1),
            new ItemCard(4,"","",1, Card.Rarity.RARE,1)
    };

    @Before
    public void setup() {deckManager = new DeckManager();}

    @Test
    public void testConstructor(){
        assertNotNull(deckManager);
        assertTrue(deckManager instanceof DeckManager);
    }

    @Test
    public void testHandlerCreateDeck(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertNotNull(test1Info);
        assertEquals("test1",test1Info.getName());
        assertEquals(0,test1Info.getId());
        assertTrue(deckManager.handlerGetDecks().contains(test1Info));
    }

    @Test
    public void testHandlerCreateMultipleDecks(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        DeckDetails test2Info = deckManager.handlerCreateDeck("test2");
        DeckDetails test3Info = deckManager.handlerCreateDeck("test3");
        assertNotNull(test1Info);
        assertNotNull(test2Info);
        assertNotNull(test3Info);
        assertEquals("test1",test1Info.getName());
        assertEquals("test2",test2Info.getName());
        assertEquals("test3",test3Info.getName());
        assertEquals(0,test1Info.getId());
        assertEquals(1,test2Info.getId());
        assertEquals(2,test3Info.getId());
        assertTrue(deckManager.handlerGetDecks().contains(test1Info));
        assertTrue(deckManager.handlerGetDecks().contains(test2Info));
        assertTrue(deckManager.handlerGetDecks().contains(test3Info));
    }

    @Test
    public void testHandlerCreateDeckFailure(){
        assertNull(deckManager.handlerCreateDeck(null));
    }

    @Test
    public void testHandlerDeleteDeck(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.handlerDeleteDeck(test1Info));
        test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.handlerGetDecks().contains(test1Info));
    }

    @Test
    public void testHandlerDeleteMultipleDecks(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        DeckDetails test2Info = deckManager.handlerCreateDeck("test2");
        DeckDetails test3Info = deckManager.handlerCreateDeck("test3");
        assertNotNull(test1Info);
        assertNotNull(test2Info);
        assertNotNull(test3Info);
        assertTrue(deckManager.handlerGetDecks().contains(test1Info));
        assertTrue(deckManager.handlerGetDecks().contains(test2Info));
        assertTrue(deckManager.handlerGetDecks().contains(test3Info));
        assertTrue(deckManager.handlerDeleteDeck(test1Info));
        assertTrue(deckManager.handlerDeleteDeck(test2Info));
        assertTrue(deckManager.handlerDeleteDeck(test3Info));
    }

    @Test
    public void testHandlerDeleteDeckFailure(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertNotNull(test1Info);
        assertTrue(deckManager.handlerGetDecks().contains(test1Info));
        assertTrue(deckManager.handlerDeleteDeck(test1Info));
        assertFalse(deckManager.handlerDeleteDeck(test1Info));
        test1Info = deckManager.handlerCreateDeck("test1");
        assertNotNull(test1Info);
        assertTrue(deckManager.handlerGetDecks().contains(test1Info));
        assertFalse(deckManager.handlerDeleteDeck(null));
        assertTrue(deckManager.handlerDeleteDeck(test1Info));
    }

    @Test
    public void testHandlerGetDecks(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        DeckDetails test2Info = deckManager.handlerCreateDeck("test2");
        DeckDetails test3Info = deckManager.handlerCreateDeck("test3");
        assertNotNull(test1Info);
        assertNotNull(test2Info);
        assertNotNull(test3Info);
        assertTrue(deckManager.handlerGetDecks().contains(test1Info));
        assertTrue(deckManager.handlerGetDecks().contains(test2Info));
        assertTrue(deckManager.handlerGetDecks().contains(test3Info));
    }

    @Test
    public void testHandlerGetDeletedDecks(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertNotNull(test1Info);
        assertTrue(deckManager.handlerGetDecks().contains(test1Info));
        assertTrue(deckManager.handlerDeleteDeck(test1Info));
        assertFalse(deckManager.handlerGetDecks().contains(test1Info));
    }

    @Test
    public void testBuilderSearchingInventory(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        //true cases
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderRemoveCard(cards[0],test1Info));
        //false cases
        deckManager.handlerDeleteDeck(test1Info);
        assertFalse(deckManager.builderAddCard(cards[0],test1Info));
        assertFalse(deckManager.builderRemoveCard(cards[0],test1Info));
    }

    @Test
    public void testBuilderAddCard(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[0]));
    }

    @Test
    public void testBuilderAddMultipleCards(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertEquals(2,(int)deckManager.builderGetCards(test1Info).get(cards[0]));
    }

    @Test
    public void testBuilderAddMultipleTypesCard(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderAddCard(cards[1],test1Info));
        assertTrue(deckManager.builderAddCard(cards[2],test1Info));
        assertEquals(2,(int)deckManager.builderGetCards(test1Info).get(cards[0]));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[1]));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[2]));
    }

    @Test
    public void testBuilderAddCardFailure(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertFalse(deckManager.builderAddCard(null,test1Info));
        assertFalse(deckManager.builderAddCard(cards[0],null));
    }

    @Test
    public void testBuilderRemoveCard(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[0]));
        assertTrue(deckManager.builderRemoveCard(cards[0],test1Info));
    }

    @Test
    public void testBuilderRemoveMultipleCards(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertEquals(4,(int)deckManager.builderGetCards(test1Info).get(cards[0]));
        assertTrue(deckManager.builderRemoveCard(cards[0],test1Info));
        assertTrue(deckManager.builderRemoveCard(cards[0],test1Info));
        assertTrue(deckManager.builderRemoveCard(cards[0],test1Info));
        assertTrue(deckManager.builderRemoveCard(cards[0],test1Info));
        assertNull(deckManager.builderGetCards(test1Info).get(cards[0]));
    }

    @Test
    public void testBuilderRemoveMultipleTypesCard(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderAddCard(cards[1],test1Info));
        assertTrue(deckManager.builderAddCard(cards[2],test1Info));
        assertTrue(deckManager.builderAddCard(cards[3],test1Info));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[0]));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[1]));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[2]));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[3]));
        assertTrue(deckManager.builderRemoveCard(cards[0],test1Info));
        assertTrue(deckManager.builderRemoveCard(cards[1],test1Info));
        assertTrue(deckManager.builderRemoveCard(cards[2],test1Info));
        assertTrue(deckManager.builderRemoveCard(cards[3],test1Info));
        assertNull(deckManager.builderGetCards(test1Info).get(cards[0]));
        assertNull(deckManager.builderGetCards(test1Info).get(cards[1]));
        assertNull(deckManager.builderGetCards(test1Info).get(cards[2]));
        assertNull(deckManager.builderGetCards(test1Info).get(cards[3]));
    }

    @Test
    public void testBuilderRemoveCardFailure(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[0]));
        assertTrue(deckManager.builderRemoveCard(cards[0],test1Info));
        assertNull(deckManager.builderGetCards(test1Info).get(cards[0]));
        assertFalse(deckManager.builderRemoveCard(cards[0],test1Info));
        assertFalse(deckManager.builderRemoveCard(null,test1Info));
        assertFalse(deckManager.builderRemoveCard(cards[0],null));
    }

    @Test
    public void testBuilderGetCards(){
        DeckDetails test1Info = deckManager.handlerCreateDeck("test1");
        assertTrue(deckManager.builderAddCard(cards[0],test1Info));
        assertTrue(deckManager.builderAddCard(cards[1],test1Info));
        assertTrue(deckManager.builderAddCard(cards[2],test1Info));
        assertTrue(deckManager.builderAddCard(cards[3],test1Info));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[0]));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[1]));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[2]));
        assertEquals(1,(int)deckManager.builderGetCards(test1Info).get(cards[3]));
    }
}
