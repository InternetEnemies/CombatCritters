package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.ICardCatalog;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CardSearchStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.Registry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.Map;

public class CardCatalogTest {
    ICardCatalog catalog;
    ICardInventory inventory;
    Registry<Card> cards;
    @Before
    public void setup() {
        inventory = new CardInventoryStub();
        cards = new Registry<>();

        catalog = new CardCatalog(new CardSearchStub(inventory,cards));
    }

    @Test
    public void getCardsEmpty(){
        assertEquals(catalog.getOwned().size(), 0);
    }

    @Test
    public void getCards(){
        Card card = new ItemCard(0,"","",1, Card.Rarity.COMMON,1);
        inventory.addCard(card);
        assertEquals((int)catalog.getOwned().get(card),1);
    }

    @Test
    public void getAll() {
        Card card = new ItemCard(0,"","",1, Card.Rarity.COMMON,1);
        inventory.addCard(card);
        cards.add(card);
        cards.add(new ItemCard(1,"","",1, Card.Rarity.COMMON,1));

        Map<Card,Integer> counts = catalog.getAll();

        assertEquals(1,(int)counts.get(cards.getSingle(0)));
        assertEquals(0,(int)counts.get(cards.getSingle(1)));
    }
}
