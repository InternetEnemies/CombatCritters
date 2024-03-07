package com.internetEnemies.combatCritters.IntegrationTests;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.ICardCatalog;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardSearchHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardCatalogTest {
    ICardCatalog catalog;
    ICardInventory inventory;
    RegistryCardHSQLDB cards;

    CardFilter filterAll = new CardFilter(
            false,
            new ArrayList<>(),
            false,
            null,
            false
    );

    List<CardOrder> orders;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();

        inventory = new CardInventoryHSQLDB(path);
        cards = new RegistryCardHSQLDB(path);

        catalog = new CardCatalog(new CardSearchHSQLDB(path));

        orders = new ArrayList<>();
    }

    @Test
    public void getCardsEmpty(){

        orders.add(CardOrder.ID);

        assertEquals(0, catalog.get(filterAll, orders).size());
    }

    @Test
    public void getCards(){
        Card card = cards.addCard(new ItemCard(0,"","",1, Card.Rarity.COMMON,1));
        inventory.addCard(card);

        orders.add(CardOrder.ID);
        assertEquals(card, catalog.get(filterAll, orders).get(0).getItem());
    }

    @Test
    public void getAll() {
        Card card0 = cards.addCard(new ItemCard(-1,"","",1, Card.Rarity.COMMON,1));
        Card card1 = cards.addCard(new ItemCard(-1,"","",1, Card.Rarity.COMMON,1));
        inventory.addCard(card0);

        orders.add(CardOrder.ID);
        List<ItemStack<Card>> counts = catalog.get(filterAll, orders);

        assertEquals(1,counts.get(0).getAmount() + counts.get(1).getAmount());
    }
}
