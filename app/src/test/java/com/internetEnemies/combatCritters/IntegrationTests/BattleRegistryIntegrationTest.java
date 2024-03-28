package com.internetEnemies.combatCritters.IntegrationTests;

import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class BattleRegistryIntegrationTest {
    private IBattleRegistry battleRegistry;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        IRegistry<Opponent> opponentDB = new BattleInfoRegistryHSQLDB(path);
        battleRegistry = new BattleRegistry(opponentDB);
    }

    @Test
    public void testGetBattlesInject(){
        assert !battleRegistry.getBattles().isEmpty();
        assert battleRegistry.getBattles().size() == 2;
    }

    @Test
    public void testGetBattles(){
        battleRegistry = new BattleRegistry();
        int opponentNum = battleRegistry.getBattles().size();
        assert opponentNum == 2;
        for(Opponent oppo: battleRegistry.getBattles()){
            assert oppo != null;
            assert oppo.getReward() != null;
        }
    }
}
