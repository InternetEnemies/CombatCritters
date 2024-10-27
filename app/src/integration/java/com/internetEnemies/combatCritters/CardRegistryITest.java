package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.inventory.cards.CardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardRegistryITest {
    RegistryCardHSQLDB registry;
    ICardRegistry cardRegistry;
    Card cardTemplate;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        registry = new RegistryCardHSQLDB(path);
        cardRegistry = new CardRegistry(registry);
        cardTemplate = new ItemCard(0,"name","image",1,Card.Rarity.COMMON,1);
    }

    @Test
    public void test_getCard(){
        Card card = registry.add(cardTemplate);
        assertEquals(card, cardRegistry.getCard(card.getId()));
    }

    @Test
    public void test_getMany(){
        List<Card> cards = new ArrayList<>();
        cards.add(registry.add(cardTemplate));
        cards.add(registry.add(cardTemplate));
        cards.add(registry.add(cardTemplate));
        cards.add(registry.add(cardTemplate));
        List<Integer> ids = cards.stream().map(Card::getId).toList();
        assertEquals(cards, cardRegistry.getCards(ids));
    }

    @Test
    public void test_getAllCards(){
        List<Card> cards = new ArrayList<>();
        cards.add(registry.add(cardTemplate));
        cards.add(registry.add(cardTemplate));
        cards.add(registry.add(cardTemplate));
        cards.add(registry.add(cardTemplate));
        assertTrue(cardRegistry.getAllCards().containsAll(cards));
    }
}
