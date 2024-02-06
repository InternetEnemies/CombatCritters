package com.internetEnemies.combatCritters.DSOUnitTests;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.ItemCard;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class PackUnitTest {
    @Test
    public void testGetters() {
        int expectedId = 1;
        String expectedName = "Test Pack";
        String expectedImage = "pack_image.png";
        List<CardSlot> expectedProbabilityList = new ArrayList<>();
        List<Card> expectedSetList = new ArrayList<>();

        Pack pack = new Pack(expectedId, expectedName, expectedImage, expectedProbabilityList, expectedSetList);

        assertEquals(expectedId, pack.getId());
        assertEquals(expectedName, pack.getName());
        assertEquals(expectedImage, pack.getImage());
        assertEquals(expectedProbabilityList, pack.getProbabilityList());
        assertEquals(expectedSetList, pack.getSetList());
    }

//    @Test
//    public void testGetProbabilityList() {
//        List<CardSlot> expectedProbabilityList = new ArrayList<>();
//        expectedProbabilityList.add(new CardSlot());
//        expectedProbabilityList.add(new CardSlot());
//
//        Pack pack = new Pack(1, "Test Pack", "pack_image.png", expectedProbabilityList, new ArrayList<>());
//
//        assertEquals(expectedProbabilityList, pack.getProbabilityList());
//    }

    @Test
    public void testGetSetList() {
        List<Card> expectedSetList = new ArrayList<>();
        expectedSetList.add(new ItemCard(1, "Card 1", "", 1, Card.Rarity.COMMON, 1));
        expectedSetList.add(new ItemCard(2, "Card 2", "", 1, Card.Rarity.COMMON, 1));

        Pack pack = new Pack(1, "Test Pack", "pack_image.png", new ArrayList<>(), expectedSetList);

        assertEquals(expectedSetList, pack.getSetList());
    }
}
