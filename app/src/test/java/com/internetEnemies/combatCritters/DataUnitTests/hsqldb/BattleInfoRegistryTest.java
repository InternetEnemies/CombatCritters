package com.internetEnemies.combatCritters.DataUnitTests.hsqldb;

import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BattleInfoRegistryTest {
    private BattleInfoRegistryHSQLDB battleInfoRegistry;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        battleInfoRegistry = new BattleInfoRegistryHSQLDB(path);
    }

    @Test
    public void testGetSingle(){
        Opponent norman = battleInfoRegistry.getSingle(1);
        assert norman.getDescription().equals("Robs Grandpa");
    }

    @Test (expected = AssertionError.class)
    public void testGetSingleOutOfBound(){
        battleInfoRegistry.getSingle(-1).getName();
    }

    @Test
    public void testGetListOf(){
        List<Integer> listOfId = new ArrayList<>();
        listOfId.add(0);
        listOfId.add(1);
        List<Opponent> temp =battleInfoRegistry.getListOf(listOfId);
        assert 1 == temp.size();
    }

    @Test
    public void testGetAll(){
        assert battleInfoRegistry.getAll().size() == 2;
        assert battleInfoRegistry.getAll().get(0).getReward().getReceivedFirstItem()!= null;
    }

}
