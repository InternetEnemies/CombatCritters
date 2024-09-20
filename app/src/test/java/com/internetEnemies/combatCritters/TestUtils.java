package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.application.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/DBInit.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC.toPath(), target.toPath());
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
    public static String getDBPath() throws IOException {
        return copyDB().getAbsolutePath().replace(".script", "");
    }
}
