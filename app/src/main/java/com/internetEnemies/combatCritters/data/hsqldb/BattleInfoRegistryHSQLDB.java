package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.Logic.CardBuilder;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.OffersDatabase;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.BattleInfoHelper;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CardHelper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.battles.Opponent;
import com.internetEnemies.combatCritters.objects.battles.RewardTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * BattleInfoRegistryHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     27/7/24
 *
 * @PURPOSE:    sql implementation of the battleInfo registry
 */

public class BattleInfoRegistryHSQLDB extends HSQLDBModel implements IRegistry<Opponent> {
    public BattleInfoRegistryHSQLDB(String dbPath) {
        super(dbPath);
    }

    @Override
    public Opponent getSingle(int id) {
        try (Connection connection = this.connection()){
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM BATTLEINFO WHERE id = ?");
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return BattleInfoHelper.opponentFromResultSet(resultSet);
            }
            else {
                return null;
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
    }

    @Override
    public List<Opponent> getListOf(List<Integer> ids) {
        List<Opponent> opponents = new ArrayList<>();
        try  (Connection connection = this.connection()){
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM BATTLEINFO WHERE id IN (");
            for (int i = 0; i < ids.size(); i++) {
                queryBuilder.append("?");
                if (i < ids.size() - 1) {
                    queryBuilder.append(",");
                }
            }
            queryBuilder.append(")");
            final PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            for (int i = 0; i < ids.size(); i++) {
                statement.setInt(i + 1, i); // Idk
            }
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                opponents.add(BattleInfoHelper.opponentFromResultSet(resultSet));
            }
        } catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return opponents;
    }

    @Override
    public List<Opponent> getAll() {
        List<Opponent> opponents = new ArrayList<>();
        try  (Connection connection = this.connection()){
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM BATTLEINFO");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                opponents.add(BattleInfoHelper.opponentFromResultSet(resultSet));
            }
        } catch (final SQLException e) {
            throw new RuntimeException("An error occurred while processing the SQL operation", e);
        }
        return opponents;
    }

    /**
     * util function for testing, id value of battleinfo is ignored
     * @param opponent opponent object to create a card from
     * @return Opponent (with real id) that was created
     */
    public Opponent addOpponent(Opponent opponent){
        Opponent newOpponent;
        try (Connection connection = this.connection()){
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO BATTLEINFO (id, NAME, DESCRIPTION, IMAGE, REWARDSID) VALUES(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            RewardTransactionRegistryHSQLDB tempRewardsDB = (RewardTransactionRegistryHSQLDB) OffersDatabase.getInstance().getRewardDB();
            int rewardsID = tempRewardsDB.add((RewardTransaction) opponent.getReward()).getId();

            stmt.setInt(1, opponent.getId());
            stmt.setString(2, opponent.getName());
            stmt.setString(3, opponent.getDescription());
            stmt.setString(4, opponent.getDescription());
            stmt.setInt(5,rewardsID);
            stmt.executeUpdate();
            ResultSet created = stmt.getGeneratedKeys();
            if (created.next()){
                int key = created.getInt(1);
                newOpponent = BattleInfoHelper.fromId(key,connection);

            } else {
                throw new RuntimeException("Failed to create new Opponent");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating an Opponent", e);
        }
        return newOpponent;
    }
}
