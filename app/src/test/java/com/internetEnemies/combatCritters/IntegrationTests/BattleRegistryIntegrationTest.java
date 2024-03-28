package com.internetEnemies.combatCritters.IntegrationTests;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.BattleInfoDatabase;
import com.internetEnemies.combatCritters.data.DeckStub;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.battles.Opponent;
import com.internetEnemies.combatCritters.presentation.battles.BattleViewModel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

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
        assert battleRegistry.getBattles().get(0).getName().equals("Norman");
    }
}
