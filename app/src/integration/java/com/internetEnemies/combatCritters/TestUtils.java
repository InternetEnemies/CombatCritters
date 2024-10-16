package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.Logic.users.UserManager;
import com.internetEnemies.combatCritters.application.Main;
import com.internetEnemies.combatCritters.data.init.SQLInitializer;
import com.internetEnemies.combatCritters.data.users.UsersDB;
import com.internetEnemies.combatCritters.objects.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TestUtils {
    private static final String DUMMY_USER = "user";
    private static final String DUMMY_PASS = "pass";
    private static final File DB_SRC = new File("src/main/resources/db/DBInit.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        SQLInitializer initializer = new SQLInitializer(target.getAbsolutePath().replace(".script", ""));
        initializer.initialize();
        return target;
    }
    public static String getDBPath() throws IOException {
        return copyDB().getAbsolutePath().replace(".script", "");
    }
    
    public static User getDummyUser(String path) {
        UserManager userManager = new UserManager(new UsersDB(path));
        return userManager.createUser(DUMMY_USER,DUMMY_PASS);
    }
}
