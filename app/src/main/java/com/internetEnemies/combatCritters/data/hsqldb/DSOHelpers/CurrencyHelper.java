package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.objects.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyHelper {

    public static Currency fromResultSet(ResultSet rs) throws SQLException {
        return new Currency(rs.getInt("balance"));
    }
}
