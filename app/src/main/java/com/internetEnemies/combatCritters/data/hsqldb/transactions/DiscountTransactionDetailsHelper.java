package com.internetEnemies.combatCritters.data.hsqldb.transactions;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DiscountTransactionDetailsHelper.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/10/24
 * 
 * @PURPOSE:    create DiscountTransactionDetails from sql results
 */
public class DiscountTransactionDetailsHelper {
    public static DiscountTransactionDetails fromResultSet(ResultSet rs) throws SQLException {
        return new DiscountTransactionDetails(
                rs.getInt("tid"),
                rs.getInt("parent"), 
                rs.getInt("discount")
        );
    }
}
