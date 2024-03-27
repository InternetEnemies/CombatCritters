package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.BattleInfoHelper;
import com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers.CardHelper;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Opponent addOpponent(){
        return null;
    }
}
