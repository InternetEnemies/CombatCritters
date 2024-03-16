package com.internetEnemies.combatCritters.LogicUnitTests;

import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.data.Registry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.Logic.CardSlotBuilder;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.Logic.PackBuilder;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class PackCatalogUnitTest {
    private PackCatalog packCatalog;

    @Before
    public void setUp() {
        // Create a stub for the pack database
        List<Card> testSetList = new ArrayList<>();
        testSetList.add(new CritterCard(0, "fortniteLover67", "" , 0, Card.Rarity.COMMON, 0, 0, null));

        CardSlotBuilder slotBuilder = new CardSlotBuilder();
        slotBuilder.addProbability(10, Card.Rarity.COMMON);

        PackBuilder builder = new PackBuilder();
        builder.addSlot(slotBuilder.build());
        builder.addSlot(slotBuilder.build());
        builder.addSlot(slotBuilder.build());

        builder.addSetList(testSetList);
        builder.setId(4);
        builder.setName("Working Pack");
        builder.setImage("testImage");

        List<Pack> packs = new ArrayList<>();
        packs.add(new Pack(0, "Test Pack", "pack_image.png", new ArrayList<>(), new ArrayList<>()));
        packs.add(new Pack(1, "Pack 1", "pack1_image.png", new ArrayList<>(), new ArrayList<>()));
        packs.add(new Pack(2, "Pack 2", "pack2_image.png", new ArrayList<>(), new ArrayList<>()));
        packs.add(new Pack(3, "Pack 3", "pack3_image.png", new ArrayList<>(), new ArrayList<>()));
        packs.add(builder.build());

        Registry<Pack> stubPackDB = new Registry<>(packs);
        packCatalog = new PackCatalog(stubPackDB);

    }

    @Test
    public void testGetPack() {
        Pack actualPack = packCatalog.getPack(0);

        assertEquals("Test Pack", actualPack.getName());
        assertEquals("pack_image.png", actualPack.getImage());
    }

    @Test
    public void testGetListOfPacks() {
        List<Pack> actualPacks = packCatalog.getListOfPacks();

        assertEquals(5, actualPacks.size());
    }
}
