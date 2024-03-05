package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.DeckValidator;
import com.internetEnemies.combatCritters.Logic.IDeckValidator;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.DeckStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.data.IDeck;

public class DeckValidatorTest {
    Card commonCritter = new CritterCard(0,"","",0, Card.Rarity.COMMON,0,0,null);
    Card uncommonCritter = new CritterCard(0,"","",0, Card.Rarity.UNCOMMON,0,0,null);
    Card rareCritter = new CritterCard(0,"","",0, Card.Rarity.RARE,0,0,null);
    Card epicCritter = new CritterCard(0,"","",0, Card.Rarity.EPIC,0,0,null);
    Card legendCritter = new CritterCard(0,"","",0, Card.Rarity.LEGENDARY,0,0,null);
    Card item = new ItemCard(0,"","",0, Card.Rarity.COMMON,0);

    IDeckValidator deckValidator;
    ICardInventory inventory;
    IDeck deck;

    private void assertInvalid(){
        assertFalse(deckValidator.validate(deck).isValid());
    }
    private void assertValid(){
        assertTrue(deckValidator.validate(deck).isValid());
    }

    private void addNCardsBoth(int n, Card card) {
        for (int i = 0; i < n; i++) {
            addCardBoth(card);
        }
    }
    private void addCardBoth(Card card) {
        this.deck.addCard(0,card);
        this.inventory.addCard(card);
    }

    @Before
    public void setup() {
        this.deck = new DeckStub(new DeckDetails(0, "test"));
        this.inventory = new CardInventoryStub();
        this.deckValidator = new DeckValidator(this.inventory);
    }

    @Test
    public void validDeck(){
        addNCardsBoth(DeckValidator.MIN_CARDS,commonCritter);
        addCardBoth(uncommonCritter);
        addCardBoth(rareCritter);
        addCardBoth(epicCritter);
        addCardBoth(legendCritter);
        addCardBoth(item);


        assertValid();
    }

    @Test
    public void testTooManyCards(){
        addNCardsBoth(DeckValidator.MAX_CARDS+1,commonCritter);
        assertInvalid();
    }

    @Test
    public void testTooFewCards(){
        for (int i = 0; i < DeckValidator.MIN_CARDS - 1; i++) {
            addCardBoth(commonCritter);
            assertInvalid();
        }
        addCardBoth(commonCritter);
        assertValid();
    }

    @Test
    public void testTooManyRare(){
        addNCardsBoth(DeckValidator.MIN_CARDS,commonCritter);
        addNCardsBoth(DeckValidator.LIMIT_RARE,rareCritter);
        assertValid();
        addCardBoth(rareCritter);
        assertInvalid();
    }
    @Test
    public void testTooManyEpic(){
        addNCardsBoth(DeckValidator.MIN_CARDS,commonCritter);
        addNCardsBoth(DeckValidator.LIMIT_EPIC,epicCritter);
        assertValid();
        deck.addCard(0,epicCritter);
        assertInvalid();
    }
    @Test
    public void testTooManyLegend(){
        addNCardsBoth(DeckValidator.MIN_CARDS,commonCritter);
        addNCardsBoth(DeckValidator.LIMIT_LEGEND,legendCritter);
        assertValid();
        deck.addCard(0,legendCritter);
        assertInvalid();
    }

    @Test
    public void tooManyItems(){
        addNCardsBoth(DeckValidator.MIN_CARDS - DeckValidator.LIMIT_ITEM, commonCritter);
        addNCardsBoth(DeckValidator.LIMIT_ITEM,item);
        assertValid();
        deck.addCard(0,item);
        assertInvalid();
    }

    @Test
    public void testOwned() {
        addNCardsBoth(DeckValidator.MIN_CARDS, commonCritter);
        assertValid();
        deck.addCard(0, commonCritter);
        assertInvalid();
    }
}
