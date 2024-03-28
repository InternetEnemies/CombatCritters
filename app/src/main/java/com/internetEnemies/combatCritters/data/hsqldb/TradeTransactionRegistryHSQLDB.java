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

/**
 * TradeTransactionRegistryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE     IRegistry implementation  for sql db of TradeTransaction
 */
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

            //insert details
            statement = connection.prepareStatement("INSERT INTO TradeInfo (tid, name, image) VALUES (?,?,?)");
            statement.setInt(1, tid);
            statement.setString(2, transaction.getName());
            statement.setString(3, transaction.getImage());
            statement.executeUpdate();

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

    /**
     * add items to the TransactionItem Table
     * @param tid id of the related transaction
     * @param stacks item stacks to add
     * @param recv is this a received(true) item? (or a given - false)
     * @param connection connection to use
     */
    public void addStacks(int tid, List<ItemStack<?>> stacks, boolean recv, Connection connection) {
        for (ItemStack<?> stack : stacks) {
            stack.getItem().accept(new TransactionItemVisitor(tid, stack.getAmount(),recv, connection));
        }
    }
}
