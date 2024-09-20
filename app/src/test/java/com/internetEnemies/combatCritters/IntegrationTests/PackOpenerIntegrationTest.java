/**
 * PackOpenerIntegrationTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     26-March-2024
 *
 * PURPOSE:     Integration Tests for PackOpener
 */
package com.internetEnemies.combatCritters.IntegrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.internetEnemies.combatCritters.Logic.inventory.packs.PackOpener;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Pack;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class PackOpenerIntegrationTest {
    private PackOpener openerTester;

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        openerTester = new PackOpener(new CardInventoryHSQLDB(path));
    }

    @Test
    public void testWeightedRandom(){
        NavigableMap<Double, Card.Rarity> regularOdds = new TreeMap<>();
        NavigableMap<Double, Card.Rarity> specialOdds = new TreeMap<>();

        regularOdds.put(7.0, Card.Rarity.COMMON);
        regularOdds.put(5.0, Card.Rarity.UNCOMMON);
        regularOdds.put(3.0, Card.Rarity.RARE);

        specialOdds.put(5.0, Card.Rarity.RARE); //always guaranteed rare

        CardSlot special = new CardSlot(specialOdds);
        CardSlot regular = new CardSlot(regularOdds);

        Card.Rarity specialResult = openerTester.randomByRarity(special);
        Card.Rarity regularResult = openerTester.randomByRarity(regular);

        assertEquals(specialResult, Card.Rarity.RARE);
        assertTrue(regularResult.equals(Card.Rarity.COMMON) || regularResult.equals(Card.Rarity.UNCOMMON) || regularResult.equals(Card.Rarity.RARE));
    }

    @Test
    public void testFindCardsOfRarity(){
        List<Card> testSetList = new ArrayList<>();
        testSetList.add(new CritterCard(0, "common", null, 0, Card.Rarity.COMMON, 0, 0, null  ));
        testSetList.add(new CritterCard(0, "rare", null, 0, Card.Rarity.RARE, 0, 0, null  ));
        testSetList.add(new CritterCard(0, "Uncommon", null, 0, Card.Rarity.UNCOMMON, 0, 0, null  ));

        Pack testPack = new Pack(0, "test", null, new ArrayList<>(), testSetList);
        List<Card> resultSet = openerTester.findCardsOfRarity(Card.Rarity.COMMON, testPack);

        for (Card c:
                resultSet) {
            assertEquals(c.getRarity(), Card.Rarity.COMMON);
        }
    }

    @Test
    public void testCardsOpened(){
        List<Card> testSetList = new ArrayList<>();
        NavigableMap<Double, Card.Rarity> odds = new TreeMap<>();

        odds.put(7.0, Card.Rarity.COMMON);
        odds.put(5.0, Card.Rarity.UNCOMMON);
        odds.put(3.0, Card.Rarity.RARE);

        CardSlot packRarityTest = new CardSlot(odds);
        List<CardSlot> cardsInPack = new ArrayList<>();

        cardsInPack.add(packRarityTest);
        cardsInPack.add(packRarityTest);
        cardsInPack.add(packRarityTest);

        testSetList.add(new CritterCard(0, "common", null, 0, Card.Rarity.COMMON, 0, 0, null  ));
        testSetList.add(new CritterCard(0, "rare", null, 0, Card.Rarity.RARE, 0, 0, null  ));
        testSetList.add(new CritterCard(0, "Uncommon", null, 0, Card.Rarity.UNCOMMON, 0, 0, null  ));

        Pack testPack = new Pack(0, "test", null, cardsInPack, testSetList);

        List<Card> resultSet = openerTester.pullCards(testPack);

        assertEquals(resultSet.size(), 3);
    }

    @Test
    public void testPullCards() {
        List<Card> testSetList = new ArrayList<>();
        NavigableMap<Double, Card.Rarity> odds = new TreeMap<>();

        odds.put(7.0, Card.Rarity.COMMON);
        odds.put(5.0, Card.Rarity.UNCOMMON);
        odds.put(3.0, Card.Rarity.RARE);

        CardSlot packRarityTest = new CardSlot(odds);
        List<CardSlot> cardsInPack = new ArrayList<>();

        cardsInPack.add(packRarityTest);
        cardsInPack.add(packRarityTest);
        cardsInPack.add(packRarityTest);

        testSetList.add(new CritterCard(1, "common1", null, 0, Card.Rarity.COMMON, 0, 0, null));
        testSetList.add(new CritterCard(2, "common2", null, 0, Card.Rarity.COMMON, 0, 0, null));
        testSetList.add(new CritterCard(3, "rare1", null, 0, Card.Rarity.RARE, 0, 0, null));
        testSetList.add(new CritterCard(4, "rare2", null, 0, Card.Rarity.RARE, 0, 0, null));
        testSetList.add(new CritterCard(5, "uncommon1", null, 0, Card.Rarity.UNCOMMON, 0, 0, null));

        Pack testPack = new Pack(0, "test", null, cardsInPack, testSetList);

        List<Card> resultSet = openerTester.pullCards(testPack);

        assertEquals(resultSet.size(), 3);

        // Check if each pulled card is of the correct rarity
        for (Card c : resultSet) {
            assertTrue(c.getRarity() == Card.Rarity.COMMON || c.getRarity() == Card.Rarity.UNCOMMON || c.getRarity() == Card.Rarity.RARE);
        }
    }

    @Test
    public void testEmptyPack() {
        Pack emptyPack = new Pack(1, "Empty Pack", null, new ArrayList<>(), new ArrayList<>());
        List<Card> result = openerTester.pullCards(emptyPack);

        assertEquals(0, result.size());
    }

    @Test
    public void testFindCardsOfRarityWithEmptyList() {
        List<Card> emptySetList = new ArrayList<>();
        Pack emptyPack = new Pack(0, "emptyPack", "", new ArrayList<>(), emptySetList);

        List<Card> resultSet = openerTester.findCardsOfRarity(Card.Rarity.COMMON, emptyPack);

        assertNotNull(resultSet);
        assertTrue(resultSet.isEmpty());
    }

    @Test
    public void testPullCardsWithEmptyPack() {
        List<CardSlot> emptyCardSlots = new ArrayList<>();
        List<Card> emptySetList = new ArrayList<>();
        Pack emptyPack = new Pack(0, "emptyPack", null, emptyCardSlots, emptySetList);

        List<Card> resultSet = openerTester.pullCards(emptyPack);

        assertNotNull(resultSet);
        assertTrue(resultSet.isEmpty());
    }

    @Test
    public void openPackReturnsCards(){
        //!this will need an integration test (afaik) for testing if the cards were added
        List<Card> testSetList = new ArrayList<>();
        NavigableMap<Double, Card.Rarity> odds = new TreeMap<>();

        odds.put(7.0, Card.Rarity.COMMON);

        CardSlot packRarityTest = new CardSlot(odds);
        List<CardSlot> cardsInPack = new ArrayList<>();

        cardsInPack.add(packRarityTest);
        cardsInPack.add(packRarityTest);
        cardsInPack.add(packRarityTest);

        testSetList.add(new CritterCard(1, "common1", null, 0, Card.Rarity.COMMON, 0, 0, null));

        Pack testPack = new Pack(0, "test", null, cardsInPack, testSetList);
        List<Card> cards = openerTester.openPack(testPack);
        assertEquals(cards.size(), 3);
    }
}
