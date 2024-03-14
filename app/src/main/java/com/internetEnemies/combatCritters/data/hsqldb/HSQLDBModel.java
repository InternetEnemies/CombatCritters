package com.internetEnemies.combatCritters.data.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class HSQLDBModel {
    protected Connection connection;
    public HSQLDBModel(String dbPath) {//todo refactor everything to use this
        try {
            this.connection = connection(dbPath);
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing a hsqldb connection",e);
        }
    }
    private Connection connection(String dbPath) throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }
}
