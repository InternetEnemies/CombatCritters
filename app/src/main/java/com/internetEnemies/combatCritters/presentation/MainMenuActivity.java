package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.databinding.ActivityMainMenuBinding;

public class MainMenuActivity extends AppCompatActivity {
    private ActivityMainMenuBinding binding;

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
}
