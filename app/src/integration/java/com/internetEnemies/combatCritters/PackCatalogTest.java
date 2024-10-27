package com.internetEnemies.combatCritters;

import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.inventory.packs.CardSlotBuilder;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackBuilder;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackCatalog;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryPackHSQLDB;
import com.internetEnemies.combatCritters.data.init.SQLInitializer;
import com.internetEnemies.combatCritters.objects.Card;
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
    IRegistry<Card> cardRegistry;
    String path;
    
    @Before
    public void setUp() throws IOException {
        path = TestUtils.getDBPath();
        packCatalog = new PackCatalog(new RegistryPackHSQLDB(path));
        this.cardRegistry = new RegistryCardHSQLDB(path);

    }

    @Test
    public void testGetPack() {
        new SQLInitializer(path).initRows();
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
    
    @Test
    public void test_createPack(){
        Pack pack = getDummyPack("Name");
        Pack created = packCatalog.createPack(pack);
        assertEquals(pack.getName(), created.getName());
    }
    
    private Pack getDummyPack(String name){
        new SQLInitializer(path).initRows();
        CardSlotBuilder cardSlotBuilder = new CardSlotBuilder();
        PackBuilder packBuilder = new PackBuilder();
        cardSlotBuilder.addProbability(0.5, Card.Rarity.COMMON);
        cardSlotBuilder.addProbability(0.5, Card.Rarity.RARE);
        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());
        packBuilder.addSlot(cardSlotBuilder.build());
        var cards = cardRegistry.getAll();
        packBuilder.addSetList(cards);
        packBuilder.setName(name);
        packBuilder.setImage("Image");
        return packBuilder.build();
    }
}
