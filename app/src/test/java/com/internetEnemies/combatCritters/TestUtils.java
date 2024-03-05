package com.internetEnemies.combatCritters;

import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;

public class TestUtils {
    private static final File DB_SRC = new File("app/src/main/java/com/internetEnemies/combatCritters/data/assets/SC.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("SC", ".script");
        Files.copy(DB_SRC, target);
        MainMenuActivity.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }

}
