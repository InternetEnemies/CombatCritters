package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IMarketDB;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.TransactionHelper;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MarketRegistryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    implement the IMarketDB interface for hsqldb
 */
public class MarketRegistryHSQLDB extends HSQLDBModel implements IMarketDB{
    private final TransactionRegistryHSQLDB registry;
    public MarketRegistryHSQLDB(String dbPath, TransactionRegistryHSQLDB registry) {
        super(dbPath);
        this.registry = registry;
    }

    @Override
    public List<MarketTransaction> getCardOffers() {
        return getOffers(TransactionRegistryHSQLDB.TYPE_MARKET_CARD);
    }

    @Override
    public List<MarketTransaction> getPackOffers() {
        return getOffers(TransactionRegistryHSQLDB.TYPE_MARKET_PACK);
    }

    @Override
    public List<MarketTransaction> getBundleOffers() {
        return getOffers(TransactionRegistryHSQLDB.TYPE_MARKET_BUNDLE);
    }

    @Override
    public void adjustDiscount(Map<Integer, Double> discounts) {
        try (Connection connection = this.connection()){

            PreparedStatement statement = connection.prepareStatement("DELETE from MarketDiscounts");
            statement.executeUpdate();

            for (Map.Entry<Integer, Double> discount : discounts.entrySet()) {
                statement = connection.prepareStatement("INSERT INTO MarketDiscounts (tid,discount) VALUES (?,?)");

                statement.setInt(1, discount.getKey());
                statement.setDouble(2, discount.getValue());
            }


        } catch (SQLException e) {
            throw new RuntimeException("error while handling discounts.", e);
        }



    }

    /**
     * get the offers in the database of a specific type
     * @param type type of offer to get
     * @return List of market transactions
     */
    private List<MarketTransaction> getOffers(String type) {
        ResultSet rs = registry.getTransactions(type);
        List<MarketTransaction> offers = new ArrayList<>();
        try (Connection connection = this.connection()){
            while(rs.next()) {
                offers.add(TransactionHelper.marketFromResultSet(rs,connection));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting market offers",e);
        }
        return offers;
    }

    /**
     * function for adding a transaction of the Card type to the db
     * @param transaction transaction to add
     */
    public void addCardOffer(MarketTransaction transaction) {
        addTransaction(TransactionRegistryHSQLDB.TYPE_MARKET_CARD,transaction);
    }
    /**
     * function for adding a transaction of the Pack type to the db
     * @param transaction transaction to add
     */
    public void addPackOffer(MarketTransaction transaction) {
        addTransaction(TransactionRegistryHSQLDB.TYPE_MARKET_PACK,transaction);
    }
    /**
     * function for adding a transaction of the Bundle type to the db
     * @param transaction transaction to add
     */
    public void addBundleOffer(MarketTransaction transaction) {
        addTransaction(TransactionRegistryHSQLDB.TYPE_MARKET_BUNDLE,transaction);
    }

    /**
     * add a transaction to the database with a specific type
     * @param type type of transaction
     * @param transaction transaction to add
     */
    private void addTransaction(String type, MarketTransaction transaction ) {
        try (Connection connection = this.connection()){
            //insert transaction
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Transactions (type) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,type);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int tid = rs.getInt(1);
            //insert received
            addReceived(tid, transaction.getReceived(),connection);
            //insert given
            addCurrency(tid, transaction.getPriceWithoutDiscount(), connection);
            // add discount?
            double discount = transaction.getDiscount();
            if(discount >0) {
                addDiscount(tid, discount, connection);
            }

        } catch (SQLException e) {
            throw new RuntimeException("error while adding a new market transaction", e);
        }
    }

    /**
     * add a list of item stacks to the TransactionItem Table
     * @param tid id of the related transaction
     * @param stacks ItemStacks to add
     * @param connection connection to use
     */
    public void addReceived(int tid, List<ItemStack<?>> stacks, Connection connection) {
        for (ItemStack<?> stack : stacks) {
            stack.getItem().accept(new TransactionItemVisitor(tid, stack.getAmount(),true, connection));
        }
    }

    /**
     * add currency to the given table
     * @param tid id of the related transaction
     * @param cost currency object to add
     * @param connection connection to use
     */
    public void addCurrency(int tid, Currency cost, Connection connection) {
        cost.accept(new TransactionItemVisitor(tid, 1, false, connection));
    }

    /**
     * add a discount to the MarketDiscount Table
     * @param tid related transaction
     * @param discount decimal discount
     * @param connection  connection to use
     */
    public void addDiscount(int tid, double discount, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO MarketDiscounts (tid,discount) VALUES (?,?)");
        statement.setInt(1, tid);
        statement.setDouble(2, discount);
        statement.executeUpdate();
    }
}
