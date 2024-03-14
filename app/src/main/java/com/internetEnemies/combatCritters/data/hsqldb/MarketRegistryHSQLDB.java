package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IMarketDB;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.TransactionHelper;
import com.internetEnemies.combatCritters.objects.MarketTransaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    //todo implement adding for testing\
}
