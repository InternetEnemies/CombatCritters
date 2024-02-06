package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;

public class CardCatalogTest {
    CardCatalog catalog;
    ICardInventory cards;
    @Before
    public void setup() {
        cards = new CardInventoryStub();
        catalog = new CardCatalog(cards);
    }

    @Test
    public void getCardsEmpty(){
        assertEquals(catalog.getCards().size(), 0);
    }

    @Test
    public void getCards(){
        Card card = new ItemCard(0,"","",1, Card.Rarity.COMMON,1);
        cards.addCard(card);
        assertEquals((int)catalog.getCards().get(card),1);
    }
}
