package com.internetEnemies.combatCritters.data.market;

import com.internetEnemies.combatCritters.objects.market.VendorDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * VendorHelper.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    convert vendor related result sets into objects
 */
public class VendorHelper {
    public static VendorDetails fromResultSet(ResultSet rs) throws SQLException {
        return new VendorDetails(
                rs.getInt("id"), 
                rs.getString("image"), 
                rs.getString("name"),
                rs.getInt("refresh")
        );
    }
}
