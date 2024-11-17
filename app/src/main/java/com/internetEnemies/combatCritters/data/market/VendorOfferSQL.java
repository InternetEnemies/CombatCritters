package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
                statement.setInt(i, offerIds.get(i-1));
                i++;
            }
            statement.setInt(i++, vendorid);
            statement.setInt(i, level);
            return statement;
        };
        
    }

    /**
     * get sql for creating a vendor offer
     * @param tid transaction id
     * @param vendorid vendor id
     * @param level min level
     */
    public static IStatementFactory createVendorOffer(int tid, int vendorid, int level){
        return connection -> {
            PreparedStatement statement = 
                    connection.prepareStatement("INSERT INTO VendorOffers (vendorId, tid, level) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, vendorid);
            statement.setInt(2, tid);
            statement.setInt(3, level);
            return statement;
        };
    }

    /**
     * get sql for creating a standard offer
     * @param tid transaction id of the offer
     */
    public static IStatementFactory createStandardOffer(int tid){
        return connection -> {
            PreparedStatement statement = 
                    connection.prepareStatement("INSERT INTO StandardOffers (tid) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, tid);
            return statement;
        };
    }

    /**
     * get sql for creating a special offer
     * @param tid transaction if of the offer
     * @param active boolean of whether the special is active
     */
    public static IStatementFactory createSpecialOffer(int tid, boolean active){
        return connection -> {
            PreparedStatement statement = 
                    connection.prepareStatement("INSERT INTO SpecialOffers (tid, active) VALUES (?, ?)");
            statement.setInt(1, tid);
            statement.setBoolean(2, active);
            return statement;
        };
    }

    /**
     * get sql for creating discount offer
     * @param tid id of the discount transaction 
     * @param parentId id of the parent offer of the discount
     * @param discount discount amount
     */
    public static IStatementFactory createDiscountOffer(int tid, int parentId, int discount) {
        return connection -> {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO DiscountOffers (tid, parent, discount) VALUES (?, ?, ?)");
            statement.setInt(1, tid);
            statement.setInt(2, parentId);
            statement.setInt(3, discount);
            return statement;
        };
    }
}
