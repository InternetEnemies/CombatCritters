package com.internetEnemies.combatCritters.DataUnitTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CardSearchStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICardSearch;
import com.internetEnemies.combatCritters.data.Registry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * CardSearchTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    test the CardSearch database interface
 */
public class CardSearchTest {
    ICardSearch cardSearch;
    ICardInventory inventory;
    Registry<Card> cards;

    Card commonCard1 = new CritterCard(0,"","",1, Card.Rarity.COMMON,0,0,null);
    Card commonCard2 = new CritterCard(1,"","",1, Card.Rarity.COMMON,0,0,null);
    Card rareCard = new CritterCard(2,"","",1, Card.Rarity.RARE,0,0,null);


    @Before
    public void setup(){
        inventory = new CardInventoryStub();
        cards = new Registry<>();
        cardSearch = new CardSearchStub(inventory,cards);
    }

    @Test
    public void noFilter(){
        cards.add(commonCard1);
        cards.add(commonCard2);
        inventory.addCard(commonCard1);

        List<ItemStack<Card>> result = cardSearch.get();
        assertEquals(2, result.size());
    }
}
