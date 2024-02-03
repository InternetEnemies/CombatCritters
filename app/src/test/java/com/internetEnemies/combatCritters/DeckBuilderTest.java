/**
 * DeckBuilderTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     02-February-2024
 *
 * PURPOSE:     Unit Test for DeckBuilder
 */

package com.internetEnemies.combatCritters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;
import com.internetEnemies.combatCritters.data.IDeck;

import java.util.ArrayList;
import java.util.Iterator;

public class DeckBuilderTest {
    private IDeckBuilder deckBuilder;

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
        assert(list().isEmpty());
        deckBuilder.createNewDeck("testDeck1");
        deckBuilder.createNewDeck("testDeck2");
        deckBuilder.createNewDeck("testDeck3");
        ArrayList<IDeck> list = list();
        assertEquals(3,list.size());
    }

    @Test
    public void testCreateNewDeck(){
        ArrayList<IDeck> list = list();
        assertEquals(0,list.size());
        deckBuilder.createNewDeck("TestDeck1");
        list = list();
        assertEquals(1,list().size());
        assertEquals("TestDeck1",list.get(0).getInfo().getName());
        deckBuilder.createNewDeck("TestDeck2");
        deckBuilder.createNewDeck("TestDeck3");
        list = list();
        assertEquals("TestDeck1",list.get(0).getInfo().getName());
        assertEquals("TestDeck2",list.get(1).getInfo().getName());
        assertEquals("TestDeck3",list.get(2).getInfo().getName());
    }

}
