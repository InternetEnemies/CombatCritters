package com.internetEnemies.combatCritters.DataUnitTests;

import org.junit.Test;
import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PacksStub;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

public class PackSubTest {
    static List<Card> setList = Arrays.asList(
            new ItemCard(1,"","",1, Card.Rarity.RARE,1),
            new ItemCard(2,"","",1, Card.Rarity.RARE,1),
            new ItemCard(3,"","",1, Card.Rarity.RARE,1),
            new ItemCard(4,"","",1, Card.Rarity.RARE,1)
    );
    private IRegistry<Pack> packs;
    Pack pack1;
    Pack pack2;
    Pack pack3;


    public void setup(){
        pack1 = new Pack(1, "pack1", null, null, setList);
        pack2 = new Pack(2, "pack2", null, null, setList);
        pack3 = new Pack(3, "pack3", null, null, setList);


        Map<Integer, Pack> packList = new TreeMap<>();
        packList.put(pack1.getId(), pack1);
        packList.put(pack2.getId(), pack2);
        packList.put(pack3.getId(), pack3);


        packs = new PacksStub(packList);
    }
    @Test
    public void testGetSingle(){
        setup();
        Pack single = packs.getSingle(1);
        assertEquals(single, pack1);

        single = packs.getSingle(2);
        assertEquals(single, pack2);

        single = packs.getSingle(0);
        assertNull(single);

    }

    @Test
    public void testGetListOf(){
        setup();
        List<Pack> comparison = new ArrayList<>();
        comparison.add(pack1);
        comparison.add(pack2);


        List<Integer> getPacks = new ArrayList<>();
        getPacks.add(1);
        getPacks.add(2);


        List<Pack> resultSet = packs.getListOf(getPacks);
        assertEquals(comparison, resultSet);

    }

    @Test
    public void testIterator() {
        // Create a sample map of packs
        Map<Integer, Pack> samplePacks = new TreeMap<>();
        samplePacks.put(1, new Pack(1, "Pack 1", null, null, null));
        samplePacks.put(2, new Pack(2, "Pack 2", null, null, null));
        samplePacks.put(3, new Pack(3, "Pack 3", null, null, null));

        PacksStub packsStub = new PacksStub(samplePacks);
        Iterator<Pack> iterator = packsStub.iterator();

        assertEquals("Pack 1", iterator.next().getName());
        assertEquals("Pack 2", iterator.next().getName());
        assertEquals("Pack 3", iterator.next().getName());
        assertFalse(iterator.hasNext());
    }

}
