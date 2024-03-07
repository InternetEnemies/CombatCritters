package com.internetEnemies.combatCritters.LogicUnitTests;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.data.Registry;
import com.internetEnemies.combatCritters.objects.Pack;
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
        List<Pack> packs = new ArrayList<>();
        packs.add(new Pack(0, "Test Pack", "pack_image.png", null, null));
        packs.add(new Pack(1, "Pack 1", "pack1_image.png", null, null));
        packs.add(new Pack(2, "Pack 2", "pack2_image.png", null, null));
        packs.add(new Pack(3, "Pack 3", "pack3_image.png", null, null));
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

        assertEquals(4, actualPacks.size());
    }
}
