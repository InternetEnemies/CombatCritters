package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.init.SQLInitializer;
import com.internetEnemies.combatCritters.data.users.UsersDB;
import com.internetEnemies.combatCritters.objects.User;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;

/**
 * this class using some hacky stuff to provide database to all test files, it's a bit weird, I'm not sorry.
 */
public class TestUtils {
    private static final String DUMMY_USER = "user";
    private static final String DUMMY_PASS = "pass";
    public static PostgreSQLContainer<?> container;

    public static String getDBPath() throws IOException {
        resetDB();
        String path = container.getJdbcUrl();
        Main.setDBPathName(path);
        SQLInitializer initializer = new SQLInitializer(path);
        if(!initializer.isDbInitialized()){// it should never be initialized, but it'll error out if it somehow is
            initializer.initTables();
        }
        
        return path;
    }
    
    public static void resetDB() {
        if (container != null) {
            container.stop();
        }
        container = new PostgreSQLContainer<>("postgres:13-alpine");
        container.start();
        container.withUrlParam("user", container.getUsername());
        container.withUrlParam("password", container.getPassword());
    }
    
    public static User getDummyUser(String path) {
        UserManager userManager = new UserManager(new UsersDB(path), _ -> {});
        return userManager.createUser(DUMMY_USER,DUMMY_PASS);
    }
    
    public static void initFull(String path) {
        SQLInitializer initializer = new SQLInitializer(path);
        initializer.initRows();
    }
}
