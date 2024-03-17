package com.internetEnemies.combatCritters.data.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * HSQLDBModel.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-17
 *
 * @PURPOSE:    abstract class that is common for all HSQLDB database interface implementations,
 *              provides connections for subclasses
 */
public abstract class HSQLDBModel {
    private final String path;
    public HSQLDBModel(String dbPath) {
        this.path= dbPath;
    }

    /**
     * get a new connection object
     * @return Connection object for the db
     */
    protected Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }
}
