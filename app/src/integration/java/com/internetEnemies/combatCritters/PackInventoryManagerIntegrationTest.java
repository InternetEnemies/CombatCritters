/**
 * PackInventoryManagerIntegrationTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     26-March-2024
 *
 * PURPOSE:     Integration Tests for PackInventoryManager
 */
package com.internetEnemies.combatCritters;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.Logic.inventory.packs.CardSlotBuilder;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackBuilder;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackInventoryManager;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryPackHSQLDB;
import com.internetEnemies.combatCritters.objects.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackInventoryManagerIntegrationTest {
    private IPackInventory packInventory;
    private ICardInventory cardInventory;
    private IPackInventoryManager manager;
    private RegistryPackHSQLDB packReg;
    private Card testCard = new CritterCard(0, "", "", 0, Card.Rarity.COMMON, 0, 0, null);

    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummy = TestUtils.getDummyUser(path);
        packInventory = new PackInventoryHSQLDB(path, dummy);
        cardInventory = new CardInventoryHSQLDB(path,dummy);
        packReg = new RegistryPackHSQLDB(path);
        manager = new PackInventoryManager(packInventory, cardInventory);
        RegistryCardHSQLDB cardReg = new RegistryCardHSQLDB(path);
        testCard = cardReg.addCard(testCard);
    }
    @Test
    public void testPackInventory(){

        CardSlotBuilder slotBuild = new CardSlotBuilder();
        slotBuild.addProbability(1, Card.Rarity.COMMON);

        PackBuilder builder = new PackBuilder();
        List<Card> setList = new ArrayList<>();
        setList.add(testCard);
        builder.addSetList(setList);
        builder.addSlot(slotBuild.build());
        builder.setId(0);

        Pack testPack = builder.build();
        int prevPackSize = packReg.getAll().size();
        testPack = packReg.addPack(testPack);
        int afterPackSize = packReg.getAll().size();
        assert packReg.getSingle(testPack.getId()).equals(testPack);
        assert afterPackSize == prevPackSize+1;
        packInventory.addPack(testPack);

        List<Pack> myPacks = manager.packsInInventory();
        assertEquals(myPacks.size(), 1);

    }

    @Test
    public void testOpener(){
        CardSlotBuilder slotBuild = new CardSlotBuilder();
        slotBuild.addProbability(1, Card.Rarity.COMMON);

        PackBuilder builder = new PackBuilder();
        List<Card> setList = new ArrayList<>();
        setList.add(testCard);
        builder.addSetList(setList);
        builder.addSlot(slotBuild.build());
        builder.setId(0);

        Pack testPack = builder.build();
        testPack = packReg.addPack(testPack);
        packInventory.addPack(testPack);

        manager.openPack(testPack);
        assertEquals(cardInventory.getCardAmount(testCard), 1);
        assertEquals(packInventory.getPackAmount(testPack), 0);
    }
    
    @Test
    public void test_getPackCounts(){
        Pack pack = getTestPack();
        Pack created = packReg.addPack(pack);
        packInventory.addPack(created);
        List<ItemStack<Pack>> packs = manager.getPackCounts();
        assertEquals(1, packs.getFirst().getAmount());
    }

    private Pack getTestPack(){
        CardSlotBuilder slotBuild = new CardSlotBuilder();
        slotBuild.addProbability(1, Card.Rarity.COMMON);

        PackBuilder builder = new PackBuilder();
        List<Card> setList = new ArrayList<>();
        setList.add(testCard);
        builder.addSetList(setList);
        builder.addSlot(slotBuild.build());

        return builder.build();
    }
}
