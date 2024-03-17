package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.Logic.TradeTransactionBuilder;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.TransactionHelper;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TradeTransactionRegistryHSQLDB extends HSQLDBModel implements IRegistry<TradeTransaction> {
    private final TransactionRegistryHSQLDB registry;
    public TradeTransactionRegistryHSQLDB(String dbPath, TransactionRegistryHSQLDB registry) {
        super(dbPath);
        this.registry = registry;
    }

    @Override
    public TradeTransaction getSingle(int id) {
        ResultSet rs = registry.getTransaction(TransactionRegistryHSQLDB.TYPE_TRADE, id);
        TradeTransaction transaction;
        try (Connection connection = this.connection()){
            if(rs.next()) {
                transaction = TransactionHelper.tradeFromResultSet(rs, connection);
            } else {
                transaction = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a trade",e);
        }
        return transaction;
    }

    @Override
    public List<TradeTransaction> getListOf(List<Integer> ids) {
        List<TradeTransaction> transactions = new ArrayList<>();
        for (Integer id : ids) {
            if(id == null) continue;
            transactions.add(getSingle(id));
        }
        return transactions;
    }

    @Override
    public List<TradeTransaction> getAll() {
        List<TradeTransaction> transactions = new ArrayList<>();
        ResultSet rs = this.registry.getTransactions(TransactionRegistryHSQLDB.TYPE_TRADE);
        try (Connection connection = this.connection()){
            while(rs.next()) {
                transactions.add(TransactionHelper.tradeFromResultSet(rs,connection));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a trade",e);
        }
        return transactions;
    }

    /**
     * add a transaction to the database, returns the actual transaction object related to the new transaction
     * @param transaction TradeTransaction to add
     */
    public TradeTransaction add(TradeTransaction transaction) {
        TradeTransactionBuilder builder = new TradeTransactionBuilder();
        try(Connection connection = this.connection()){
            //insert Transaction
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Transactions (type) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, TransactionRegistryHSQLDB.TYPE_TRADE);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int tid = rs.getInt(1);

            //insert given
            addStacks(tid, transaction.getGiven(), false, connection);

            //insert recieved
            addStacks(tid, transaction.getReceived(), true, connection);

            //rebuild
            builder.fromTransaction(transaction);
            builder.setID(tid);
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a trade to the db",e);
        }
        return builder.build();
    }
    public void addStacks(int tid, List<ItemStack<?>> stacks, boolean recv, Connection connection) {
        for (ItemStack<?> stack : stacks) {
            stack.getItem().accept(new TransactionItemVisitor(tid, stack.getAmount(),recv, connection));
        }
    }
}
