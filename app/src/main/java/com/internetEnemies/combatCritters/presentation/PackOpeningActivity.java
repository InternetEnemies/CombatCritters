package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.databinding.ActivityPackOpeningBinding;

/**
 * DeckBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     UI for opening different packs.
 */

public class PackOpeningActivity extends AppCompatActivity {

    private ActivityPackOpeningBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPackOpeningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBackToDeckBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.buttonPack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(PackOpeningActivity.this, CardsOpenedActivity.class);
                 intent.putExtra("packNumber", 0);
                 startActivity(intent);
            }
        });

        binding.buttonPack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PackOpeningActivity.this, CardsOpenedActivity.class);
                intent.putExtra("packNumber", 1);
                startActivity(intent);
            }
        });

        binding.buttonPack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PackOpeningActivity.this, CardsOpenedActivity.class);
                intent.putExtra("packNumber", 2);
                startActivity(intent);
            }
        });
    }
}
