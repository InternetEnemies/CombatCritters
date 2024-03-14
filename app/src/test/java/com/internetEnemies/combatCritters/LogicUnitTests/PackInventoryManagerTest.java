package com.internetEnemies.combatCritters.LogicUnitTests;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.Logic.CardSlotBuilder;
import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.PackBuilder;
import com.internetEnemies.combatCritters.Logic.PackInventoryManager;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.data.PackInventoryStub;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

public class PackInventoryManagerTest {
    private IPackInventory packInventory;
    private ICardInventory cardInventory;
    private IPackInventoryManager manager;


    @Before
    void setup(){
        packInventory = new PackInventoryStub();
        cardInventory = new CardInventoryStub();
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
}
