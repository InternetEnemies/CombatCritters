package com.internetEnemies.combatCritters.LogicUnitTests;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.data.PacksStub;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class PackCatalogUnitTest {
    private PackCatalog packCatalog;
    private IRegistry<Pack> stubPackDB;

    @Before
    public void setUp() {
        // Create a stub for the pack database
        stubPackDB = new PackDatabaseStub();
        packCatalog = new PackCatalog(stubPackDB);
        // Create a stub for the pack database
        Map<Integer, Pack> packsMap = new HashMap<>();
        packsMap.put(1, new Pack(1, "Test Pack", "pack_image.png", null, null));
        stubPackDB = new PacksStub(packsMap);
    }

    @Test
    public void testGetPack() {
        // Calling the method under test
        Pack actualPack = packCatalog.getPack(1);

        // Asserting that the returned pack matches the expected pack
        assertEquals("Test Pack", actualPack.getName());
        assertEquals("pack_image.png", actualPack.getImage());
        // Add more assertions as needed
    }

    @Test
    public void testGetListOfPacks() {
        // Calling the method under test
        List<Pack> actualPacks = packCatalog.getListOfPacks();

        // Asserting that the returned list contains the expected packs
        assertEquals(3, actualPacks.size()); // Assuming there are 3 packs in the stub
        // Add more assertions as needed
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
            // Implement this method to return a list of packs for the specified list of IDs
            List<Pack> packs = new ArrayList<>();
            for (int id : ids) {
                // You can customize the behavior to return different packs based on the IDs if needed
                packs.add(getSingle(id));
            }
            return packs;
        }
    }
}
