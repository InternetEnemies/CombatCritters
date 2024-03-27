package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

import java.sql.Connection;
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
    private final TransactionRegistryHSQLDB registry;
    public RewardTransactionRegistryHSQLDB(String dbPath, TransactionRegistryHSQLDB registry) {
        super(dbPath);
        this.registry = registry;
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

    /**
     * add a transaction to the database, returns the actual transaction object related to the new transaction
     * @param transaction RewardTransaction to add
     */
    public RewardTransaction add(RewardTransaction transaction){
        return null;
    }

    /**
     * add items to the TransactionItem Table
     * @param tid id of the related transaction
     * @param stacks item stacks to add
     * @param connection connection to use
     */
    public void addStacks(int tid, List<ItemStack<?>> stacks, Connection connection) {
        for (ItemStack<?> stack : stacks) {
            stack.getItem().accept(new TransactionItemVisitor(tid, stack.getAmount(),true, connection));
        }
    }
}
