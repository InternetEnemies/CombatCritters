package com.internetEnemies.combatCritters.LogicUnitTests;

import static junit.framework.TestCase.assertEquals;

import com.internetEnemies.combatCritters.Logic.CardDeconstructor;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;

import org.junit.Before;
import org.junit.Test;

public class CardDeconstructorUnitTest {
    private ICurrencyInventory currencyInventory;
    private ICardInventory cardInventory;
    @Before
    public void setup(){
        currencyInventory = new CurrencyInventoryStub();
        currencyInventory.setBalance(new Currency(0));
        cardInventory = new CardInventoryStub();
    }

    @Test
    public void TestDeconstruct(){
        Card common = new CritterCard(0, "", "", 0, Card.Rarity.COMMON, 0 , 0, null);
        Card uncommon = new CritterCard(1, "", "", 0, Card.Rarity.UNCOMMON, 0 , 0, null);
        Card rare = new CritterCard(2, "", "", 0, Card.Rarity.RARE, 0 , 0, null);
        Card epic = new CritterCard(3, "", "", 0, Card.Rarity.EPIC, 0 , 0, null);
        Card legendary = new CritterCard(4, "", "", 0, Card.Rarity.LEGENDARY, 0 , 0, null);

        cardInventory.addCard(common);
        cardInventory.addCard(uncommon);
        cardInventory.addCard(rare);
        cardInventory.addCard(epic);
        cardInventory.addCard(legendary);

        CardDeconstructor destructor = new CardDeconstructor(cardInventory, currencyInventory);

        destructor.deconstruct(common);
        assertEquals(currencyInventory.getCurrentBalance().getAmount(), CardDeconstructor.COMMON_VALUE);
        assertEquals(cardInventory.getCardAmount(common), 0);

        destructor.deconstruct(uncommon);
        assertEquals(currencyInventory.getCurrentBalance().getAmount(), CardDeconstructor.COMMON_VALUE + CardDeconstructor.UNCOMMON_VALUE);
        assertEquals(cardInventory.getCardAmount(uncommon), 0);

        destructor.deconstruct(rare);
        assertEquals(currencyInventory.getCurrentBalance().getAmount(), CardDeconstructor.COMMON_VALUE + CardDeconstructor.UNCOMMON_VALUE + CardDeconstructor.RARE_VALUE);
        assertEquals(cardInventory.getCardAmount(rare), 0);

        destructor.deconstruct(epic);
        assertEquals(currencyInventory.getCurrentBalance().getAmount(), CardDeconstructor.COMMON_VALUE + CardDeconstructor.UNCOMMON_VALUE + CardDeconstructor.RARE_VALUE + CardDeconstructor.EPIC_VALUE);
        assertEquals(cardInventory.getCardAmount(epic), 0);

        destructor.deconstruct(legendary);
        assertEquals(currencyInventory.getCurrentBalance().getAmount(), CardDeconstructor.COMMON_VALUE + CardDeconstructor.UNCOMMON_VALUE + CardDeconstructor.RARE_VALUE + CardDeconstructor.EPIC_VALUE + CardDeconstructor.LEGENDARY_VALUE);
        assertEquals(cardInventory.getCardAmount(legendary), 0);

    }
}
