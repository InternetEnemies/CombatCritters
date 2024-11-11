package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * VendorOfferSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/1/24
 * 
 * @PURPOSE:    provide sql for Offers db
 */
public class VendorOfferSQL {
    /**
     * gets sql statement for getting a vendor offer from its id
     * @param offerId id of the offer
     * @param vendorid id of the vendor 
     */
    public static IStatementFactory getVendorOffer(int offerId, int vendorid, int level) {
        return connection -> {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM VendorOffers INNER JOIN Transactions ON VendorOffers.tid = Transactions.id WHERE VendorOffers.tid = ? AND VendorOffers.vendorid = ? AND VendorOffers.level <= ?");
            preparedStatement.setInt(1, offerId);
            preparedStatement.setInt(2, vendorid);
            preparedStatement.setInt(3, level);
            return preparedStatement;
        };
    }

    /**
     * gets sql statement for getting all of a vendors offers
     * @param vendorid id of vendor to get offers for
     */
    public static IStatementFactory getVendorOffers(int vendorid, int level) {
        return connection -> {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM StandardOffers INNER JOIN VendorOffers ON StandardOffers.tid = VendorOffers.tid INNER JOIN Transactions ON VendorOffers.tid = Transactions.id WHERE VendorOffers.vendorid = ? AND VendorOffers.level <= ?");
            preparedStatement.setInt(1, vendorid);
            preparedStatement.setInt(2, level);
            return preparedStatement;
        };
    }

    /**
     * sql statement for getting active special offers
     * @param vendorid vendor to get special offers for
     * @param level max level to get
     */
    public static IStatementFactory getVendorSpecials(int vendorid, int level) {
        return connection -> {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM SpecialOffers INNER JOIN VendorOffers ON SpecialOffers.tid = VendorOffers.tid INNER JOIN Transactions ON VendorOffers.tid = Transactions.id WHERE VendorOffers.vendorid = ? AND VendorOffers.level <= ? AND SpecialOffers.active = true");
            preparedStatement.setInt(1, vendorid);
            preparedStatement.setInt(2, level);
            return preparedStatement;
        };
    }

    /**
     * sql statement for getting vendor discount offers
     * @param vendorid id of the vendor
     * @param level max level
     */
    public static IStatementFactory getVendorDiscounts(int vendorid, int level) {
        return connection -> {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM DiscountOffers INNER JOIN VendorOffers ON DiscountOffers.tid = VendorOffers.tid WHERE VendorOffers.vendorid = ? AND VendorOffers.level <= ?");
            statement.setInt(1, vendorid);
            statement.setInt(2, level);
            return statement;
        };
    }

    /**
     * sql statement for getting offers from list of ids
     * @param vendorid vendor to get for
     * @param level max level
     * @param offerIds offer ids to get
     */
    public static IStatementFactory getVendorOffersFromIds(int vendorid, int level, List<Integer> offerIds ){
        return connection -> {
            // Prep statement
            String stmt = String.format("SELECT * FROM VendorOffers INNER JOIN Transactions ON VendorOffers.tid = Transactions.id WHERE VendorOffers.tid IN (%s) AND VendorOffers.vendorid = ? AND VendorOffers.level <= ?",
                    offerIds.stream()
                            .map(_-> "?")
                            .collect(Collectors.joining(", "))
            );
            PreparedStatement statement = connection
                    .prepareStatement(stmt);//! sql here is safe since we aren't inserting values just param placeholders
            // Fill statement args
            int i = 1;
            while (i <= offerIds.size()) {
                statement.setInt(1, offerIds.get(i-1));
                i++;
            }
            statement.setInt(i++, vendorid);
            statement.setInt(i, level);
            return statement;
        };
    }
}
