/**
 * CardDeconstructorIntegrationTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     26-March-2024
 *
 * PURPOSE:     Integration Tests for CardDeconstructor
 */
package com.internetEnemies.combatCritters;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.internetEnemies.combatCritters.Logic.inventory.cards.CardDeconstructor;
import com.internetEnemies.combatCritters.Logic.inventory.cards.ICardDeconstructor;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class CardDeconstructorIntegrationTest {
    private ICurrencyInventory currencyInventory;
    private ICardInventory cardInventory;
    private Card common = new CritterCard(0, "", "", 0, Card.Rarity.COMMON, 0 , 0, null);
    private Card uncommon = new CritterCard(1, "", "", 0, Card.Rarity.UNCOMMON, 0 , 0, null);
    private Card rare = new CritterCard(2, "", "", 0, Card.Rarity.RARE, 0 , 0, null);
    private Card epic = new CritterCard(3, "", "", 0, Card.Rarity.EPIC, 0 , 0, null);
    private Card legendary = new CritterCard(4, "", "", 0, Card.Rarity.LEGENDARY, 0 , 0, null);
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        currencyInventory = new CurrencyInventoryHSQLDB(path);
        currencyInventory.setBalance(new Currency(0));
        cardInventory = new CardInventoryHSQLDB(path, dummy);
        RegistryCardHSQLDB cardReg = new RegistryCardHSQLDB(path);
        common = cardReg.addCard(common);
        uncommon = cardReg.addCard(uncommon);
        rare = cardReg.addCard(rare);
        epic = cardReg.addCard(epic);
        legendary = cardReg.addCard(legendary);
    }

    @Test
    @Ignore
    public void TestDeconstruct(){
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
    @Test
    @Ignore
    public void testMultiple(){
        ICardDeconstructor destructor = new CardDeconstructor(cardInventory, currencyInventory);

        cardInventory.addCard(common);
        cardInventory.addCard(common);
        cardInventory.addCard(common);
        cardInventory.addCard(common);
        cardInventory.addCard(common);


        assertEquals(destructor.getResult(common,2), CardDeconstructor.COMMON_VALUE * 2);
        destructor.deconstruct(common, 2);
        assertEquals(currencyInventory.getCurrentBalance().getAmount(), CardDeconstructor.COMMON_VALUE * 2);
        assertEquals(cardInventory.getCardAmount(common), 3);

    }

    @Test
    public void cardOwnedTrue() {
        ICardDeconstructor destructor = new CardDeconstructor(cardInventory, currencyInventory);
        cardInventory.addCard(common);
        assertTrue(destructor.isOwned(common));
    }

    @Test
    public void cardsOwnedFalse() {
        ICardDeconstructor destructor = new CardDeconstructor(cardInventory, currencyInventory);
        Card card = new ItemCard(0,"","",1, Card.Rarity.COMMON,1);
        assertFalse(destructor.isOwned(card));
    }
}
