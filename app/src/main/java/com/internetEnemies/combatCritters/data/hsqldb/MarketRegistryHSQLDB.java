package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IMarketDB;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.TransactionHelper;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Transaction;
import com.internetEnemies.combatCritters.objects.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    private List<MarketTransaction> getOffers(String type) {
        ResultSet rs = registry.getTransactions(type);
        List<MarketTransaction> offers = new ArrayList<>();
        try {
            while(rs.next()) {
                offers.add(TransactionHelper.marketFromResultSet(rs,this.connection));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting market offers",e);
        }
        return offers;
    }

    public void addCardOffer(MarketTransaction transaction) {
        addTransaction(TransactionRegistryHSQLDB.TYPE_MARKET_CARD,transaction);
    }
    public void addPackOffer(MarketTransaction transaction) {
        addTransaction(TransactionRegistryHSQLDB.TYPE_MARKET_PACK,transaction);
    }
    public void addBundleOffer(MarketTransaction transaction) {
        addTransaction(TransactionRegistryHSQLDB.TYPE_MARKET_BUNDLE,transaction);
    }

    private void addTransaction(String type, MarketTransaction transaction ) {
        try {
            //insert transaction
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Transactions (type) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,type);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int tid = rs.getInt(1);
            //insert received
            addReceived(tid, transaction.getReceived());
            //insert given
            addCurrency(tid, transaction.getPriceWithoutDiscount());
            // add discount?
            double discount = transaction.getDiscount();
            if(discount >0) {
                addDiscount(tid, discount);
            }

        } catch (SQLException e) {
            throw new RuntimeException("error while adding a new market transaction", e);
        }
    }

    public void addReceived(int tid, List<ItemStack<?>> stacks) {
        for (ItemStack<?> stack : stacks) {
            stack.getItem().accept(new TransactionItemVisitor(tid, stack.getAmount(),true, this.connection));
        }
    }

    public void addCurrency(int tid, Currency cost) {
        cost.accept(new TransactionItemVisitor(tid, 1, false, this.connection));
    }

    public void addDiscount(int tid, double discount) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO MarketDiscounts (tid,discount) VALUES (?,?)");
        statement.setInt(1, tid);
        statement.setDouble(2, discount);
        statement.executeUpdate();
    }
}
