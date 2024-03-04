package com.internetEnemies.combatCritters.LogicUnitTests;

import static junit.framework.TestCase.assertEquals;

import com.internetEnemies.combatCritters.Logic.CardDestructor;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;

import org.junit.Before;
import org.junit.Test;

public class CardDeconstructorUnitTest {
    private ICurrencyInventory currencyInventory;
    private ICardInventory cardInventory;
    @Before
    public void setup(){
        currencyInventory = new CurrencyInventoryStub();
        cardInventory = new CardInventoryStub();
    }

    @Test
    public void TestDeconstruct(){
        Card common = new CritterCard(0, "", "", 0, Card.Rarity.COMMON, 0 , 0, null);
        Card uncommon = new CritterCard(1, "", "", 0, Card.Rarity.UNCOMMON, 0 , 0, null);
        Card rare = new CritterCard(2, "", "", 0, Card.Rarity.RARE, 0 , 0, null);
        Card epic = new CritterCard(3, "", "", 0, Card.Rarity.EPIC, 0 , 0, null);
        Card legendary = new CritterCard(4, "", "", 0, Card.Rarity.LEGENDARY, 0 , 0, null);

        CardDestructor destructor = new CardDestructor(cardInventory, currencyInventory);
        cardInventory.addCard(common);
        cardInventory.addCard(uncommon);
        cardInventory.addCard(rare);
        cardInventory.addCard(epic);
        cardInventory.addCard(legendary);

        destructor.deconstruct(common, 0);
        assertEquals(currencyInventory.getCurrentBalance(0).getAmount(), 5);
        assertEquals(cardInventory.getCardAmount(common), 0);

        destructor.deconstruct(uncommon, 0);
        assertEquals(currencyInventory.getCurrentBalance(0).getAmount(), 12);
        assertEquals(cardInventory.getCardAmount(uncommon), 0);

        destructor.deconstruct(rare , 0);
        assertEquals(currencyInventory.getCurrentBalance(0).getAmount(), 27);
        assertEquals(cardInventory.getCardAmount(rare), 0);

        destructor.deconstruct(epic, 0);
        assertEquals(currencyInventory.getCurrentBalance(0).getAmount(), 47);
        assertEquals(cardInventory.getCardAmount(epic), 0);

        destructor.deconstruct(legendary, 0);
        assertEquals(currencyInventory.getCurrentBalance(0).getAmount(), 77);
        assertEquals(cardInventory.getCardAmount(legendary), 0);

    }
}
