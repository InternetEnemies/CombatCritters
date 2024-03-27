package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.Logic.TradeTransactionBuilder;
import com.internetEnemies.combatCritters.Logic.battles.RewardTransactionBuilder;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.TransactionHelper;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.battles.IRewardTransactionBuilder;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        ResultSet rs = registry.getTransaction(TransactionRegistryHSQLDB.TYPE_REWARD, id);
        RewardTransaction transaction;
        try (Connection connection = this.connection()){
            if(rs.next()) {
                transaction = TransactionHelper.rewardFromResultSet(rs, connection);
            } else {
                transaction = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a trade",e);
        }
        return transaction;
    }

    @Override
    public List<RewardTransaction> getListOf(List<Integer> ids) {
        List<RewardTransaction> transactions = new ArrayList<>();
        for (Integer id : ids) {
            if(id == null) continue;
            transactions.add(getSingle(id));
        }
        return transactions;
    }

    @Override
    public List<RewardTransaction> getAll() {
        List<RewardTransaction> transactions = new ArrayList<>();
        ResultSet rs = this.registry.getTransactions(TransactionRegistryHSQLDB.TYPE_REWARD);
        try (Connection connection = this.connection()){
            while(rs.next()) {
                transactions.add(TransactionHelper.rewardFromResultSet(rs,connection));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a trade",e);
        }
        return transactions;
    }

    /**
     * add a transaction to the database, returns the actual transaction object related to the new transaction
     * @param transaction RewardTransaction to add
     */
    public RewardTransaction add(RewardTransaction transaction){
        IRewardTransactionBuilder builder = new RewardTransactionBuilder();
        try(Connection connection = this.connection()){
            //insert Transaction
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Transactions (type) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, TransactionRegistryHSQLDB.TYPE_REWARD);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int tid = rs.getInt(1);

            //insert recieved
            addStacks(tid, transaction.getReceived(), connection);

            //rebuild
            builder.fromTransaction(transaction);
            builder.setID(tid);
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a trade to the db",e);
        }
        return builder.build();
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
