package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.init.SQLInitializer;
import com.internetEnemies.combatCritters.data.users.UsersDB;
import com.internetEnemies.combatCritters.objects.User;

import java.io.IOException;

public class TestUtils {
    private static final String DUMMY_USER = "user";
    private static final String DUMMY_PASS = "pass";

    public static String getDBPath() throws IOException {
        String path = "jdbc:postgresql://localhost:5432/critter_db?user=admin&password=admin";
        Main.setDBPathName(path);
        SQLInitializer initializer = new SQLInitializer(path);
        if(!initializer.isDbInitialized()){//todo remove this and switch to the container dbs
            initializer.initTables();
        }
        
        return path;
    }
    
    public static User getDummyUser(String path) {
        UserManager userManager = new UserManager(new UsersDB(path));
        return userManager.createUser(DUMMY_USER,DUMMY_PASS);
    }
}
