package com.internetEnemies.combatCritters.data.init;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class SQLInitializer extends HSQLDBModel {
    public SQLInitializer(String dbPath) {
        super(dbPath);
    }

    /**
     * initialize the full database if the database isn't initialized
     */
    public void initFull() {
        if (!isDbInitialized()){
            initTables();
            initRows();
        }
    }

    /**
     * check if the database is initialized
     */
    public boolean isDbInitialized() {
        boolean isInitialized = false;
        //check if the config table exists, if it exists we assume the db is initialized
        try (Connection connection = this.connection()){
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "users", null);
            if (tables.next()) {
                isInitialized = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking db initialization state", e);
        }
        return isInitialized;
    }

    /**
     * initial the database rows
     */
    public void initRows(){
        try{
            runScript("db/Rows.sql");
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error initializing db rows", e);
        }
    }

    /**
     * initialize the database tables
     */
    public void initTables(){
        try{
            runScript("db/Tables.sql");
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error initializing db tables", e);
        }
    }

    /**
     * runs the script at the resource location
     */
    private void runScript(String resource) throws SQLException, IOException {
        try (Connection connection = this.connection();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(resource)){
            assert stream != null;
            InputStreamReader streamReader = new InputStreamReader(stream);
            ScriptRunner runner = new ScriptRunner(connection, false, true);
            runner.runScript(streamReader);
            streamReader.close();
        }
    }
}
