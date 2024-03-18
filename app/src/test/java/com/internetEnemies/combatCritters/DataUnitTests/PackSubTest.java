package com.internetEnemies.combatCritters.DataUnitTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.Registry;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackSubTest {
    static List<Card> setList = Arrays.asList(
            new ItemCard(0,"","",1, Card.Rarity.RARE,1),
            new ItemCard(1,"","",1, Card.Rarity.RARE,1),
            new ItemCard(2,"","",1, Card.Rarity.RARE,1),
            new ItemCard(3,"","",1, Card.Rarity.RARE,1)
    );
    private IRegistry<Pack> packs;
    Pack pack1;
    Pack pack2;
    Pack pack3;


    @Before
    public void setup(){
        pack1 = new Pack(0, "pack1", "", new ArrayList<>(), setList);
        pack2 = new Pack(1, "pack2", "", new ArrayList<>(), setList);
        pack3 = new Pack(2, "pack3", "", new ArrayList<>(), setList);


        List<Pack> packList = new ArrayList<>();
        packList.add(pack1);
        packList.add(pack2);
        packList.add(pack3);


        packs = new Registry<>(packList);
    }
    @Test
    public void testGetSingle(){
        Pack single = packs.getSingle(0);
        assertEquals(single, pack1);

        single = packs.getSingle(1);
        assertEquals(single, pack2);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetSingleOOB() {
        packs.getSingle(-1);
    }

    @Test
    public void testGetListOf(){
        List<Pack> comparison = new ArrayList<>();
        comparison.add(pack1);
        comparison.add(pack2);


        List<Integer> getPacks = new ArrayList<>();
        getPacks.add(0);
        getPacks.add(1);


        List<Pack> resultSet = packs.getListOf(getPacks);
        assertEquals(comparison, resultSet);

    }
}
