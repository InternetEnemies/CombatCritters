package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import com.internetEnemies.combatCritters.data.hsqldb.RewardTransactionBuilder;
import com.internetEnemies.combatCritters.data.OffersDatabase;
import com.internetEnemies.combatCritters.objects.battles.IRewardTransactionBuilder;
import com.internetEnemies.combatCritters.objects.battles.Opponent;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BattleInfoHelper.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     27/7/24
 *
 * @PURPOSE:    help the database to make opponent objects
 */

public class BattleInfoHelper {
    /**
     * get a opponent object form the result set
     * @param rs result set to get
     * @return opponent object from the ResultSet
     */
    public static Opponent opponentFromResultSet(final ResultSet rs) throws SQLException{
        final  int id = rs.getInt("id");
        final String name = rs.getString("name");
        final String description = rs.getString("DESCRIPTION");
        final String imageRef = rs.getString("IMAGE");
        final int rewardsID = rs.getInt("REWARDSID");


        IRewardTransactionBuilder builder = new RewardTransactionBuilder();
        builder.fromTransaction(OffersDatabase.getInstance().getRewardDB().getSingle(rewardsID));
        RewardTransaction reward = builder.build();
        return new Opponent(id,name,description,imageRef,reward);
    }
}
