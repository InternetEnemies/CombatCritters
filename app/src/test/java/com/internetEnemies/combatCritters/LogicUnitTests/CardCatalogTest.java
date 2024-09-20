package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.inventory.cards.CardCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardCatalog;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CardSearchStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.Registry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CardCatalogTest {
    ICardCatalog catalog;
    ICardInventory inventory;
    Registry<Card> cards;

    CardFilter filterAll = new CardFilter(
            false,
            new ArrayList<>(),
            false,
            null,
            false
    );

    List<CardOrder> orders;

    @Before
    public void setup() {
        inventory = new CardInventoryStub();
        cards = new Registry<>();

        catalog = new CardCatalog(new CardSearchStub(inventory,cards));

        orders = new ArrayList<>();
    }

    @Test
    public void getCardsEmpty(){

        orders.add(CardOrder.ID);

        assertEquals(0, catalog.get(filterAll, orders).size());
    }

    @Test
    public void getCards(){
        Card card = new ItemCard(0,"","",1, Card.Rarity.COMMON,1);
        inventory.addCard(card);

        orders.add(CardOrder.ID);
        assertEquals(card, catalog.get(filterAll, orders).get(0).getItem());
    }

    @Test
    public void getAll() {
        Card card = new ItemCard(0,"","",1, Card.Rarity.COMMON,1);
        inventory.addCard(card);
        cards.add(card);
        cards.add(new ItemCard(1,"","",1, Card.Rarity.COMMON,1));

        orders.add(CardOrder.ID);
        List<ItemStack<Card>> counts = catalog.get(filterAll, orders);

        assertEquals(1,counts.get(0).getAmount() + counts.get(1).getAmount());
    }
}
