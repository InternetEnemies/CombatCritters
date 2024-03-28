package com.internetEnemies.combatCritters.DataUnitTests.hsqldb;

import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class BattleInfoRegistryTest {
    private BattleInfoRegistryHSQLDB temp;
    @Before
    public void testOpponentSize() throws IOException {
        String path = TestUtils.getDBPath();
        temp = new BattleInfoRegistryHSQLDB(path);
    }

    @Test
    public void testhaha(){
        assert temp.getAll().size() == 2;
    }
}
