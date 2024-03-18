package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.objects.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CurrencyHelper.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    Helper functions for the sql database
 */
public class CurrencyHelper {

    /**
     * get a currency from the result of a PlayerInfo check
     * @param rs result set to use
     * @return Currency from rs
     */
    public static Currency fromResultSet(ResultSet rs) throws SQLException {
        return new Currency(rs.getInt("balance"));
    }
}
