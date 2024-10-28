package com.internetEnemies.combatCritters.data.hsqldb.queryProviders;

import com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers.SQLExecutor.IStatementFactory;
import com.internetEnemies.combatCritters.objects.User;

import java.sql.PreparedStatement;

/**
 * UserProfileSQL.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/28/24
 * 
 * @PURPOSE:    SQL for PlayerInfo Table
 */
public class PlayerInfoSQL {
    public static IStatementFactory createPlayerInfo(User user, int startCurrency) {
        return connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PlayerInfo (userid, balance) VALUES (?,?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, startCurrency);
            return preparedStatement;
        };
    }
}
