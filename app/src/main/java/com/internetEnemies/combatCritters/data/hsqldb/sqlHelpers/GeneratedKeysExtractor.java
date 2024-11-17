package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * GeneratedKeysExtractor.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/16/24
 * 
 * @PURPOSE:    ResultExtractor for extracting the generated key from an sql update
 */
public class GeneratedKeysExtractor {
    /**
     * get the generated key from a sql insert
     */
    public static Integer extractKey(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(1);
    }
}
