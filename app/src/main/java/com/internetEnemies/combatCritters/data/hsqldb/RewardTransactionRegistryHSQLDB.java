package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

import java.util.List;

/**
 * RewardTransactionRegistryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-27
 *
 * @PURPOSE     IRegistry implementation  for sql db of RewardTransaction
 */

public class RewardTransactionRegistryHSQLDB extends HSQLDBModel implements IRegistry<RewardTransaction> {

    public RewardTransactionRegistryHSQLDB(String dbPath) {
        super(dbPath);
    }

    @Override
    public RewardTransaction getSingle(int id) {
        return null;
    }

    @Override
    public List<RewardTransaction> getListOf(List<Integer> ids) {
        return null;
    }

    @Override
    public List<RewardTransaction> getAll() {
        return null;
    }
}
