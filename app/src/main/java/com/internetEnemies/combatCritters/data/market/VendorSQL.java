package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * VendorSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    provide SQL statements for VendorDB
 */
public class VendorSQL {
    public static PreparedStatement getVendors(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM vendors");
    }
    
    public static IStatementFactory getVendor(int vendorId) {
        return connection -> {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM vendors WHERE id = ?");
            stmt.setInt(1, vendorId);
            return stmt;
        };
    }
}
