package com.internetEnemies.combatCritters.LogicUnitTests;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.objects.Pack;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class PackCatalogUnitTest {
    private PackCatalog packCatalog;
    private IRegistry<Pack> stubPackDB;

    @Before
    public void setUp() {
        // Create a stub for the pack database
        stubPackDB = new PackDatabaseStub();
        packCatalog = new PackCatalog(stubPackDB);
    }

    @Test
    public void testGetPack() {
        Pack actualPack = packCatalog.getPack(1);

        assertEquals("Test Pack", actualPack.getName());
        assertEquals("pack_image.png", actualPack.getImage());
    }

    @Test
    public void testGetListOfPacks() {
        List<Pack> actualPacks = packCatalog.getListOfPacks();

        assertEquals(3, actualPacks.size());
    }

    private class PackDatabaseStub implements IRegistry<Pack> {

        @Override
        public Pack getSingle(int id) {
            // Return a mock Pack object for testing
            return new Pack(id, "Test Pack", "pack_image.png", null, null);
        }

        @Override
        public List<Pack> getAll() {
            // Return a list of mock Pack objects for testing
            List<Pack> packs = new ArrayList<>();
            packs.add(new Pack(1, "Pack 1", "pack1_image.png", null, null));
            packs.add(new Pack(2, "Pack 2", "pack2_image.png", null, null));
            packs.add(new Pack(3, "Pack 3", "pack3_image.png", null, null));
            return packs;
        }

        @Override
        public List<Pack> getListOf(List<Integer> ids) {
            List<Pack> packs = new ArrayList<>();
            for (int id : ids) {
                packs.add(getSingle(id));
            }
            return packs;
        }
    }
}
