package com.internetEnemies.combatCritters.data.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * HSQLDBDebug.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/11/24
 *
 * @PURPOSE:    Functions useful for debugging the hsqldb (Class should never be called in production)
 */
public class HSQLDBDebug {
    private final Connection connection;
    public HSQLDBDebug(String path){
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println();
        }
    } // source -> https://stackoverflow.com/a/28165814

    public void printCardsTable() {
        try {
            PreparedStatement s = connection.prepareStatement("SELECT * FROM CARDS");
            ResultSet rs = s.executeQuery();
            printResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
