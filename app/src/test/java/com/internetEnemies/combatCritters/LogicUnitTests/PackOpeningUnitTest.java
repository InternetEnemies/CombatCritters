package com.internetEnemies.combatCritters.LogicUnitTests;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.PackOpener;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Collections;

public class PackOpeningUnitTest {
    private PackOpener openerTester = new PackOpener();

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

        Pack testPack = new Pack(0, "test", null, null, testSetList);
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
    public void testConstructorWithoutPack() {
        PackOpener openerWithoutPack = new PackOpener(); // PackOpener without providing a Pack
        assertNotNull(openerWithoutPack);

        // Create a default Pack (empty Pack) for testing
        Pack defaultPack = new Pack(0, "Default Pack", null, Collections.emptyList(), Collections.emptyList());

        List<Card> result = openerWithoutPack.pullCards(defaultPack);
        assertEquals(0, result.size());
    }

    @Test
    public void testFindCardsOfRarityWithEmptyList() {
        List<Card> emptySetList = new ArrayList<>();
        Pack emptyPack = new Pack(0, "emptyPack", null, null, emptySetList);

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
}
