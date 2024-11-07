package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.PreparedStatement;

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
    
}
