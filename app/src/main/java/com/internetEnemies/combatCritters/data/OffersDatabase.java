/**
 * OffersDatabase.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    A fake Database for offers in marketDB and TradeRegistry
 *              relies on PackCardDatabase
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.hsqldb.MarketRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.RewardTransactionRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.TradeTransactionRegistryHSQLDB;
import com.internetEnemies.combatCritters.data.hsqldb.TransactionRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

public class OffersDatabase {
    private static OffersDatabase INSTANCE;

    private final IRegistry<TradeTransaction> tradesDB;
    private final IMarketDB marketDB;
    private final IRegistry<RewardTransaction> rewardDB;

    public static synchronized OffersDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OffersDatabase();
        }
        return INSTANCE;
    }

    public IRegistry<TradeTransaction> getTradesDB() {
        return tradesDB;
    }

    public IMarketDB getMarketDB() {
        return marketDB;
    }

    public IRegistry<RewardTransaction> getRewardDB(){
        return rewardDB;
    }

    private OffersDatabase() {
        String path = Main.getDBPathName();
        TransactionRegistryHSQLDB registry = new TransactionRegistryHSQLDB(path);
        tradesDB = new TradeTransactionRegistryHSQLDB(path,registry);
        marketDB = new MarketRegistryHSQLDB(path,registry);
        rewardDB = new RewardTransactionRegistryHSQLDB(path,registry);
    }
}
