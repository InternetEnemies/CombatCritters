package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.databinding.ActivityMainMenuBinding;


/**
 * MainMenuActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     The main menu user interface. It allows navigation to the various screens of the
 *               application through buttons.
 */

public class MainMenuActivity extends AppCompatActivity {
    private ActivityMainMenuBinding binding;

    private static String dbName = "CombatCrittersDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onCreateSetup();
    }

    private void onCreateSetup() {
        binding.buttonToDeckBuilder.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MainMenuActivity.this, DeckBuilderActivity.class);
            startActivity(intent);
        });

        binding.buttonToPacks.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MainMenuActivity.this, PackOpeningActivity.class);
            startActivity(intent);
        });

    }

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

}
