package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.Logic.MarketTransactionBuilder;
import com.internetEnemies.combatCritters.Logic.TradeTransactionBuilder;
import com.internetEnemies.combatCritters.Logic.battles.RewardTransactionBuilder;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.data.hsqldb.TransactionRegistryHSQLDB;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TransactionHelper.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE: Helper functions for the database related to Transactions
 */
public class TransactionHelper {
    /**
     * get a market transaction from a result set
     * @param rs result set to use
     * @param connection connection to use
     * @return MarketTransaction from rs
     */
    public static MarketTransaction marketFromResultSet(ResultSet rs, Connection connection) throws SQLException {
        MarketTransactionBuilder builder = new MarketTransactionBuilder();
        int tid = rs.getInt("id");
        builder.setID(tid);
        // add items to recieved and add the price
        getItems(tid,connection,builder::addToReceived,stack -> {
            if(!(stack.getItem() instanceof Currency)){
                throw new RuntimeException("Invalid Market Transaction in database"); //shouldn't happen
            }
            builder.setPrice(((Currency)stack.getItem()));
        });
        builder.setDiscount(getDiscount(tid, connection));
        return builder.build();
    }

    /**
     * get a TradeTransaction from a result set
     * @param rs ResultSet to use
     * @param connection connection to use
     * @return TradeTransaction from the resultset
     */
    public static TradeTransaction tradeFromResultSet(ResultSet rs, Connection connection) throws SQLException {
        TradeTransactionBuilder builder = new TradeTransactionBuilder();
        int tid = rs.getInt("id");
        builder.setID(tid);
        getItems(tid, connection, builder::addToReceived, builder::addToGiven);
        return builder.build();
    }

    /**
     * get a RewardTransaction from a result set
     * @param rs ResultSet to use
     * @param connection connection to use
     * @return RewardTransaction from the resultset
     */
    public static RewardTransaction rewardFromResultSet(ResultSet rs, Connection connection) throws SQLException{
        RewardTransactionBuilder builder = new RewardTransactionBuilder();
        int tid = rs.getInt("id");
        builder.setID(tid);
        getItems(tid, connection, builder::addToReceived, e->{
            assert e.getItem() != null;
        });
        return builder.build();
    }

    /**
     * get the items and process them with callbacks
     * @param tid transaction id
     * @param connection connection to use to get items
     * @param recv callback to use with items that are of receive type
     * @param give callback to use with items that are of give type
     */
    private static void getItems(int tid, Connection connection, itemHandler recv, itemHandler give) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM TransactionItem WHERE tid = ?");
        statement.setInt(1,tid);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) { // handle each of the items related to the transaction
            IItem item = getItem(connection, rs);
            int amount = rs.getInt("amount");
            ItemStack<?> stack = new ItemStack<>(item,amount);
            if(rs.getBoolean("recv")){
                recv.handleItem(stack);
            } else {
                give.handleItem(stack);
            }
        }

    }

    /**
     * get an item from a TransactionItem ResultSet
     * @param connection connection to use
     * @param rs result set to use
     * @return IItem from rs
     */
    private static IItem getItem(Connection connection, ResultSet rs) throws SQLException {
        IItem item;
        switch (rs.getString("type")) {
            case TransactionRegistryHSQLDB.ITEM_CARD:
                item = CardHelper.fromId(rs.getInt("cardId"), connection);
                break;
            case TransactionRegistryHSQLDB.ITEM_PACK:
                item = PackHelper.fromId(rs.getInt("packId"),connection);
                break;
            case TransactionRegistryHSQLDB.ITEM_CURRENCY:
                item = new Currency(rs.getInt("currency"));
                break;
            default:
                throw new RuntimeException("Invalid Transaction Item Type in database");

        }
        return item;
    }

    /**
     * get the discount related to a transaction
     * @param tid id of the transaction
     * @param connection connection to use
     * @return discount or 0 if none found
     */
    private static double getDiscount(int tid, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT (discount) FROM MarketDiscounts WHERE tid = ?");
        statement.setInt(1,tid);
        ResultSet rs = statement.executeQuery();

        double discount;
        if (rs.next()) {
            discount = rs.getDouble("discount");
        } else {
            discount = 0;
        }
        rs.next();
        return discount;
    }
}

/**
 * callback interface used by the getItems method
 */
interface itemHandler {
    void handleItem(ItemStack<?> stack);
}