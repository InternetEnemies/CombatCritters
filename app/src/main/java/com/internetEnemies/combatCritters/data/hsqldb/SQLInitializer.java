package com.internetEnemies.combatCritters.data.hsqldb;

import com.internetEnemies.combatCritters.data.init.ScriptRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLInitializer extends HSQLDBModel{
    public SQLInitializer(String dbPath) {
        super(dbPath);
    }
    
    public void initialize() {
        try(
                Connection connection = this.connection();
                InputStream tableStream = this.getClass().getClassLoader().getResourceAsStream("db/Tables.sql");
                InputStream rowStream = this.getClass().getClassLoader().getResourceAsStream("db/Rows.sql");
                ) {
            ScriptRunner runner = new ScriptRunner(connection, true, false);

            InputStreamReader reader = new InputStreamReader(tableStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            runner.runScript(bufferedReader);
            reader.close();
            
            reader = new InputStreamReader(rowStream);
            bufferedReader = new BufferedReader(reader);
            runner.runScript(bufferedReader);
            reader.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error initializing db", e);
        }
    }
}
