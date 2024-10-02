package com.internetEnemies.combatCritters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.internetEnemies.combatCritters.Logic.inventory.decks.DeckValidator;
import com.internetEnemies.combatCritters.Logic.inventory.decks.IDeckValidator;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.DeckInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;

import com.internetEnemies.combatCritters.objects.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DeckValidatorTest {
    Card commonCritter;
    Card uncommonCritter;
    Card rareCritter;
    Card epicCritter;
    Card legendCritter;
    Card item;

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
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        this.deck = new DeckInventoryHSQLDB(path).createDeck("TestDeck");
        this.inventory = new CardInventoryHSQLDB(path, dummy);
        this.deckValidator = new DeckValidator(this.inventory);
        RegistryCardHSQLDB cardDb = new RegistryCardHSQLDB(path);

        // # Card Init
        commonCritter = cardDb.addCard(new CritterCard(0,"","",0, Card.Rarity.COMMON,0,0,null));
        uncommonCritter = cardDb.addCard(new CritterCard(0,"","",0, Card.Rarity.UNCOMMON,0,0,null));
        rareCritter = cardDb.addCard(new CritterCard(0,"","",0, Card.Rarity.RARE,0,0,null));
        epicCritter = cardDb.addCard(new CritterCard(0,"","",0, Card.Rarity.EPIC,0,0,null));
        legendCritter = cardDb.addCard(new CritterCard(0,"","",0, Card.Rarity.LEGENDARY,0,0,null));
        item = cardDb.addCard(new ItemCard(0,"","",0, Card.Rarity.COMMON,0));
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
