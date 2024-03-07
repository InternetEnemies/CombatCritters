package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.objects.Pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * RegistryPackHSQLDB.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/7/24
 *
 * @PURPOSE:    sql implementation of the pack registry
 */
public class RegistryPackHSQLDB implements IRegistry<Pack> {
    private final String dbPath;

    public RegistryPackHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public Pack getSingle(int id) {
        Pack pack;
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cards WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                pack = PackHelper.packFromResultSet(rs,connection);
            } else {
                pack = null;
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException("error getting a single pack", e);
        }
        return pack;
    }

    @Override
    public List<Pack> getAll() {
        List<Pack> packs = new ArrayList<>();
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM Packs");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                packs.add(PackHelper.packFromResultSet(rs,connection));
            }
        } catch (final SQLException e) {
            throw new RuntimeException("error getting all packs", e);
        }
        return packs;
    }

    @Override
    public List<Pack> getListOf(List<Integer> ids) {
        throw new RuntimeException("Not implemented for HSQLDB"); //this should be done at some point or the function should be removed (i dont think it is used)
    }
}
