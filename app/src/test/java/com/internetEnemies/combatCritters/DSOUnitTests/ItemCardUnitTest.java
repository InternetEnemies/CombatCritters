package com.internetEnemies.combatCritters.DSOUnitTests;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemCardUnitTest {
    @Test
    public void testGetEffectId() {
        int expectedEffectId = 123;
        ItemCard itemCard = new ItemCard(1, "Item Card", "Image", 2, Card.Rarity.UNCOMMON, expectedEffectId);
        assertEquals(expectedEffectId, itemCard.getEffectId());
    }

    @Test
    public void testInheritedFields() {
        int expectedId = 1;
        String expectedName = "Item Card";
        String expectedImage = "Image";
        int expectedPlayCost = 2;
        Card.Rarity expectedRarity = Card.Rarity.UNCOMMON;

        ItemCard itemCard = new ItemCard(expectedId, expectedName, expectedImage, expectedPlayCost, expectedRarity, 123);

        assertEquals(expectedId, itemCard.getId());
        assertEquals(expectedName, itemCard.getName());
        assertEquals(expectedImage, itemCard.getImage());
        assertEquals(expectedPlayCost, itemCard.getPlayCost());
        assertEquals(expectedRarity, itemCard.getRarity());
    }
}
