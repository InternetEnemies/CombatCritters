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

import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;

public class DeckBuilderTest {
    private IDeckBuilder deckBuilder;
    @Before
    public void setup(){ deckBuilder = new DeckBuilder(); }

    @Test
    public void testDeckBuilderConstructor(){
        assert(deckBuilder instanceof DeckBuilder);
    }

    @Test
    public void testCreateNewDeck(){
        deckBuilder.createNewDeck("TestDeck");
        
    }

    @Test
    public void testDuplicateDeckName(){

    }

}
