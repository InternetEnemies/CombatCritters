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
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

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
}
