package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;

import java.sql.PreparedStatement;

/**
 * VendorRepSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    provide sql for VendorRep Table
 */
public class VendorRepSQL {

    /**
     * sql for getting reputation entry
     */
    public static IStatementFactory getRep(int vendorId, int userid) {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM VendorRep WHERE vendorid = ? AND userid = ?");
            statement.setInt(1, vendorId);
            statement.setInt(2, userid);
            return statement;
        };
    }
    
    public static IStatementFactory updateRep(int vendorId, int userid, int reputation) {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO VendorRep (userid,vendorid,reputation) VALUES (?,?,?) ON CONFLICT (userid, vendorid) DO UPDATE SET reputation = VendorRep.reputation + ?");
            statement.setInt(1, userid);
            statement.setInt(2, vendorId);
            statement.setInt(3, reputation);
            statement.setInt(4, reputation);
            return statement;
        };
    }
}
