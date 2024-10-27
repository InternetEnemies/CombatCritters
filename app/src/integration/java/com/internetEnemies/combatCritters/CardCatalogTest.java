package com.internetEnemies.combatCritters;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.Logic.inventory.cards.CardCatalog;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardCatalog;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardSearchHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.*;

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
    CardFilter filterOwned; 

    List<CardOrder> orders;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        filterOwned = new CardFilter(
                false,
                new ArrayList<>(),
                true,
                null,
                false,
                dummy
        );

        inventory = new CardInventoryHSQLDB(path, dummy);
        cards = new RegistryCardHSQLDB(path);

        catalog = new CardCatalog(new CardSearchHSQLDB(path));

        orders = new ArrayList<>();
    }

    @Test
    public void getCards(){
        Card card = cards.add(new ItemCard(0,"","",1, Card.Rarity.COMMON,1));
        inventory.addCard(card);

        int cardID = card.getId();
        Card tempCard = cards.getSingle(cardID);
        assert tempCard.equals(card);

        orders.add(CardOrder.ID);
        assertEquals(card, catalog.get(filterAll, orders).get(card.getId()-1).getItem());
    }

    @Test
    public void getAll() {
        int prevNum = cards.getAll().size();
        Card card0 = cards.add(new ItemCard(-1,"","",1, Card.Rarity.COMMON,1));
        Card card1 = cards.add(new ItemCard(-1,"","",1, Card.Rarity.COMMON,1));
        int afterNum = cards.getAll().size();
        assert afterNum == prevNum+2;
        inventory.addCard(card0);

        orders.add(CardOrder.ID);
        List<ItemStack<Card>> counts = catalog.get(filterOwned, orders);
        int sum = 0;
        for (ItemStack<Card> stack : counts) {
            sum += stack.getAmount();
        }

        assertEquals(1,sum);
    }
}
