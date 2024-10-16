package com.internetEnemies.combatCritters.data.init;

import com.internetEnemies.combatCritters.data.hsqldb.HSQLDBModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLInitializer extends HSQLDBModel {
    public SQLInitializer(String dbPath) {
        super(dbPath);
    }

    /**
     * initialize the full database
     */
    public void initFull() {
        initTables();
        initRows();
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
            InputStreamReader streamReader = new InputStreamReader(stream);
            ScriptRunner runner = new ScriptRunner(connection, true, true);
            runner.runScript(streamReader);
            streamReader.close();
        }
    }
}
