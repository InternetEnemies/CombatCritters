/**
 * PackInventoryManagerIntegrationTest.java
 * COMP 3350 A02
 * Project      combat critters
 * @created     26-March-2024
 *
 * PURPOSE:     Integration Tests for PackInventoryManager
 */
package com.internetEnemies.combatCritters.IntegrationTests;

import static org.junit.Assert.assertEquals;

import com.internetEnemies.combatCritters.Logic.CardSlotBuilder;
import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.PackBuilder;
import com.internetEnemies.combatCritters.Logic.PackInventoryManager;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Pack;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackInventoryManagerIntegrationTest {
    private IPackInventory packInventory;
    private ICardInventory cardInventory;
    private IPackInventoryManager manager;


    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        packInventory = new PackInventoryHSQLDB(path);
        cardInventory = new CardInventoryHSQLDB(path);
        manager = new PackInventoryManager(packInventory, cardInventory);


    }
    @Test
    public void testPackInventory(){

        Card testCard = new CritterCard(0, "", "", 0, Card.Rarity.COMMON, 0, 0, null);
        CardSlotBuilder slotBuild = new CardSlotBuilder();
        slotBuild.addProbability(1, Card.Rarity.COMMON);

        PackBuilder builder = new PackBuilder();
        List<Card> setList = new ArrayList<>();
        setList.add(testCard);
        builder.addSetList(setList);
        builder.addSlot(slotBuild.build());
        builder.setId(0);

        Pack testPack = builder.build();

        packInventory.addPack(testPack);

        List<Pack> myPacks = manager.packsInInventory();
        assertEquals(myPacks.size(), 1);

    }

    @Test
    public void testOpener(){
        Card testCard = new CritterCard(0, "", "", 0, Card.Rarity.COMMON, 0, 0, null);
        CardSlotBuilder slotBuild = new CardSlotBuilder();
        slotBuild.addProbability(1, Card.Rarity.COMMON);

        PackBuilder builder = new PackBuilder();
        List<Card> setList = new ArrayList<>();
        setList.add(testCard);
        builder.addSetList(setList);
        builder.addSlot(slotBuild.build());
        builder.setId(0);

        Pack testPack = builder.build();

        packInventory.addPack(testPack);

        manager.openPack(testPack);
        assertEquals(cardInventory.getCardAmount(testCard), 1);
        assertEquals(packInventory.getPackAmount(testPack), 0);
    }
}
