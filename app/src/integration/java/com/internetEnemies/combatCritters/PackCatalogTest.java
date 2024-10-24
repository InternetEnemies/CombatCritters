package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.inventory.packs.PackCatalog;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryPackHSQLDB;
import com.internetEnemies.combatCritters.objects.Pack;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * PackCatalogTest.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    Integration tests for PackCatalog
 */
public class PackCatalogTest {
    private PackCatalog packCatalog;

    @Before
    public void setUp() throws IOException {
        String path = TestUtils.getDBPath();
        packCatalog = new PackCatalog(new RegistryPackHSQLDB(path));

    }

    @Test
    @Ignore
    public void testGetPack() {
        Pack actualPack = packCatalog.getPack(1);

        assertNotNull(actualPack);
        assertNotNull(actualPack.getName());
        assertNotNull(actualPack.getSetList());
        assertTrue(actualPack.getSetList().size() > 0);
        assertEquals(1, actualPack.getId());
    }

    @Test
    public void testGetListOfPacks() {
        List<Pack> actualPacks = packCatalog.getListOfPacks();
        for (Pack pack : actualPacks) {
            assertNotNull(pack);
        }

    }
}
