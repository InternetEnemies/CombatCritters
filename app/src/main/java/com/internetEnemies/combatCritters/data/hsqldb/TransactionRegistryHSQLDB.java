package com.internetEnemies.combatCritters.data.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRegistryHSQLDB extends HSQLDBModel{
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
