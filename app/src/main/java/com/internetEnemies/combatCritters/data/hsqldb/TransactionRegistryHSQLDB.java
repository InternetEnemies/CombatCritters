package com.internetEnemies.combatCritters.data.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TransactionRegistryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    provide an interface for getting transactions from the database
 */
public class TransactionRegistryHSQLDB extends HSQLDBModel{
    /*
    These constants define what values to use as types in the database Transaction and TransactionItem Tables
     */
    public static final String TYPE_MARKET_CARD = "m_card";
    public static final String TYPE_MARKET_PACK = "m_pack";
    public static final String TYPE_MARKET_BUNDLE = "m_bundle";
    public static final String TYPE_TRADE = "t";

    public static final String ITEM_CARD = "card";
    public static final String ITEM_PACK = "pack";
    public static final String ITEM_CURRENCY = "currency";

    public TransactionRegistryHSQLDB(String dbPath) {
        super(dbPath);
    }

    /**
     * get a result set for all the transactions of a certain type
     * @param type type of transaction to get
     * @return ResultSet for the query
     */
    public ResultSet getTransactions(String type) {
        ResultSet rs;
        try (Connection connection = this.connection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Transactions WHERE type = ?");
            statement.setString(1, type);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a transaction",e);
        }

        return rs;
    }

    /**
     * get a transaction from its type and id
     * @param type type of the transaction
     * @param id id of the transaction
     * @return ResultSet at for transaction
     */
    public ResultSet getTransaction(String type, int id) {
        ResultSet rs;
        try (Connection connection = this.connection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Transactions WHERE type = ? AND id = ?");
            statement.setString(1, type);
            statement.setInt(2, id);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a transaction",e);
        }

        return rs;

    }
}
