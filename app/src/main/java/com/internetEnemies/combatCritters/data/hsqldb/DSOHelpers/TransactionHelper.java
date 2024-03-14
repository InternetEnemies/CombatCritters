package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.Logic.MarketTransactionBuilder;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.data.hsqldb.TransactionRegistryHSQLDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionHelper {
    public static MarketTransaction marketFromResultSet(ResultSet rs, Connection connection) throws SQLException {
        MarketTransactionBuilder builder = new MarketTransactionBuilder();
        int tid = rs.getInt("id");
        getItems(tid,connection,builder::addToReceived,stack -> {
            if(stack.getItem() instanceof Currency){
                throw new RuntimeException("Invalid Market Transaction in database"); //shouldn't happen
            }
            builder.setPrice(((Currency)stack.getItem()));
        });
        builder.setDiscount(getDiscount(tid, connection));
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
        while(rs.next()) {
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

    private static double getDiscount(int tid, Connection connection){
        //todo implment this properly with the deal rotation in mind
        throw new RuntimeException("Not Implemented");
    }
}

interface itemHandler {
    void handleItem(ItemStack<?> stack);
}