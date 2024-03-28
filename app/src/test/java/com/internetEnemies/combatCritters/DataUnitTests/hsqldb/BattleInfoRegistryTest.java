package com.internetEnemies.combatCritters.DataUnitTests.hsqldb;

import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class BattleInfoRegistryTest {
    private BattleInfoRegistryHSQLDB temp;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        temp = new BattleInfoRegistryHSQLDB(path);
    }

    @Test
    public void testhaha(){
        Opponent norman = temp.getSingle(1);
        assert norman.getDescription().equals("Someones Grandpa");
    }
}
