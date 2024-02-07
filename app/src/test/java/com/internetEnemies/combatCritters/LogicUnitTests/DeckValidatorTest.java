package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.DeckValidator;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.ArrayList;
import java.util.List;

public class DeckValidatorTest {
    Card commonCritter = new CritterCard(0,"","",0, Card.Rarity.COMMON,0,0,null);
    Card rareCritter = new CritterCard(0,"","",0, Card.Rarity.RARE,0,0,null);
    Card epicCritter = new CritterCard(0,"","",0, Card.Rarity.EPIC,0,0,null);
    Card legendCritter = new CritterCard(0,"","",0, Card.Rarity.LEGENDARY,0,0,null);
    Card item = new ItemCard(0,"","",0, Card.Rarity.COMMON,0);

    List<Card> deck;

    private void assertInvalid(){
        assertFalse(DeckValidator.validateDeck(deck).isValid());
    }
    private void assertValid(){
        assertTrue(DeckValidator.validateDeck(deck).isValid());
    }

    private void addNCards(int n, Card card) {
        for (int i = 0; i < n; i++) {
            deck.add(card);
        }
    }

    @Before
    public void setup() {
        deck = new ArrayList<>();
    }

    @Test
    public void validDeck(){
        addNCards(DeckValidator.MIN_CARDS,commonCritter);
        deck.add(rareCritter);
        deck.add(epicCritter);
        deck.add(legendCritter);
        deck.add(item);


        assertValid();
    }

    @Test
    public void testTooManyCards(){
        addNCards(DeckValidator.MAX_CARDS+1,commonCritter);
        assertInvalid();
    }

    @Test
    public void testTooFewCards(){
        for (int i = 0; i < DeckValidator.MIN_CARDS - 1; i++) {
            deck.add(commonCritter);
            assertInvalid();
        }
        deck.add(commonCritter);
        assertValid();
    }

    @Test
    public void testTooManyRare(){
        addNCards(DeckValidator.MIN_CARDS,commonCritter);
        addNCards(DeckValidator.LIMIT_RARE,rareCritter);
        assertValid();
        deck.add(rareCritter);
        assertInvalid();
    }
    @Test
    public void testTooManyEpic(){
        addNCards(DeckValidator.MIN_CARDS,commonCritter);
        addNCards(DeckValidator.LIMIT_EPIC,epicCritter);
        assertValid();
        deck.add(epicCritter);
        assertInvalid();
    }
    @Test
    public void testTooManyLegend(){
        addNCards(DeckValidator.MIN_CARDS,commonCritter);
        addNCards(DeckValidator.LIMIT_LEGEND,legendCritter);
        assertValid();
        deck.add(legendCritter);
        assertInvalid();
    }
}
