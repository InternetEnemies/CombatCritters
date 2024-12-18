package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.Logic.transaction.ITransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionHandlerDeprecated;
import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCardFactory;
import com.internetEnemies.combatCritters.Logic.battles.events.EventSystem;
import com.internetEnemies.combatCritters.Logic.battles.registry.BattleRegistry;
import com.internetEnemies.combatCritters.Logic.battles.registry.IBattleRegistry;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.BattleInfoRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CardInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.CurrencyInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.PackInventoryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RegistryCardHSQLDB;
import com.internetEnemies.combatCritters.objects.User;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class BattleRegistryIntegrationTest {
    private IBattleRegistry battleRegistry;
    private ITransactionHandlerDeprecated transactionHandler;
    @Before
    public void setup() throws IOException {
        String path = TestUtils.getDBPath();
        User dummyUser = TestUtils.getDummyUser(path);
        transactionHandler = new TransactionHandlerDeprecated(
                new CardInventoryHSQLDB(path, dummyUser),
                new PackInventoryHSQLDB(path, dummyUser),
                new CurrencyInventoryHSQLDB(path, dummyUser)
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
