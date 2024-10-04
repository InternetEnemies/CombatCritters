package com.internetEnemies.combatCritters.LogicUnitTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.internetEnemies.combatCritters.Logic.inventory.cards.CardRegistry;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardRegistry;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CardRegistryTest {
    IRegistry<Card> registry;
    ICardRegistry cardRegistry;
    Card card;
    @Before
    public void setup() {
        this.registry = (IRegistry<Card>) mock(IRegistry.class);
        cardRegistry = new CardRegistry(registry);
        card = new ItemCard(0,"name","image",1,Card.Rarity.COMMON,1);
    }
    
    @Test
    public void test_getCard(){
        when(registry.getSingle(0)).thenReturn(card);
        assertEquals(card, cardRegistry.getCard(0));
    }
    
    @Test
    public void test_getMany(){
        List<Integer> ids = List.of(0,0,0,0);
        List<Card> cards = List.of(card,card,card,card);
        when(registry.getListOf(ids)).thenReturn(cards);
        assertEquals(cards, cardRegistry.getCards(ids));
    }
    
    @Test
    public void test_getAllCards(){
        List<Card> cards = List.of(card,card);
        when(registry.getAll()).thenReturn(cards);
        assertEquals(cards, cardRegistry.getAllCards());
    }
}
