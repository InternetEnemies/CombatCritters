package com.internetEnemies.combatCritters.IntegrationTests;

import com.internetEnemies.combatCritters.Logic.ITransactionHandler;
import com.internetEnemies.combatCritters.Logic.TransactionHandler;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;
import com.internetEnemies.combatCritters.TestUtils;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class BattleRegistryIntegrationTest {
    private IBattleRegistry battleRegistry;
    private ITransactionHandler transactionHandler;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        transactionHandler = new TransactionHandler(
                new CardInventoryHSQLDB(path),
                new PackInventoryHSQLDB(path),
                new CurrencyInventoryHSQLDB(path)
        );
        IRegistry<Opponent> opponentDB = new BattleInfoRegistryHSQLDB(path);
        EventSystem eventSystem = new EventSystem();
        battleRegistry = new BattleRegistry(opponentDB, transactionHandler, new RegistryCardHSQLDB(path),new BattleCardFactory(eventSystem),eventSystem);
    }

    @Test
    public void testGetBattles(){
        for(Opponent oppo: battleRegistry.getBattles()){
            assert oppo != null;
            assert oppo.getReward() != null;
        }
    }
}
