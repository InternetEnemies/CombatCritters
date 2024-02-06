package com.internetEnemies.combatCritters.DSOUnitTests;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Card;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class CritterCardUnitTest {
    @Test
    public void testGetDamage() {
        int expectedDamage = 10;
        CritterCard critterCard = new CritterCard(1, "Card Name", "Image", 3, Card.Rarity.COMMON, expectedDamage, 20, Arrays.asList(1, 2, 3));
        assertEquals(expectedDamage, critterCard.getDamage());
    }

    @Test
    public void testGetHealth() {
        int expectedHealth = 20;
        CritterCard critterCard = new CritterCard(1, "Card Name", "Image", 3, Card.Rarity.COMMON, 10, expectedHealth, Arrays.asList(1, 2, 3));
        assertEquals(expectedHealth, critterCard.getHealth());
    }

    @Test
    public void testGetAbilities() {
        List<Integer> expectedAbilities = Arrays.asList(1, 2, 3);
        CritterCard critterCard = new CritterCard(1, "Card Name", "Image", 3, Card.Rarity.COMMON, 10, 20, expectedAbilities);
        assertEquals(expectedAbilities, critterCard.getAbilities());
    }

    @Test
    public void testInheritedFields() {
        int expectedId = 1;
        String expectedName = "Card Name";
        String expectedImage = "Image";
        int expectedPlayCost = 3;
        Card.Rarity expectedRarity = Card.Rarity.COMMON;

        CritterCard critterCard = new CritterCard(expectedId, expectedName, expectedImage, expectedPlayCost, expectedRarity, 10, 20, Arrays.asList(1, 2, 3));

        assertEquals(expectedId, critterCard.getId());
        assertEquals(expectedName, critterCard.getName());
        assertEquals(expectedImage, critterCard.getImage());
        assertEquals(expectedPlayCost, critterCard.getPlayCost());
        assertEquals(expectedRarity, critterCard.getRarity());
    }
}
