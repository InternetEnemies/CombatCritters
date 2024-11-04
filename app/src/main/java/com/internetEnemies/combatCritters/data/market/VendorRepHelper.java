package com.internetEnemies.combatCritters.data.market;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * VendorRepHelper.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    provide vendor reputation from sql results
 */
public class VendorRepHelper {
    /**
     * gets reputation from a result set
     * !NOTE: This helper assumes the result set cursor is before the entry
     */
    public static Integer fromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt("reputation");
        } else {
            return 0;
        }
    }
}
