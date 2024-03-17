package com.internetEnemies.combatCritters.data.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class HSQLDBModel {
    private final String path;
    public HSQLDBModel(String dbPath) {
        this.path= dbPath;
    }
    protected Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }
}
