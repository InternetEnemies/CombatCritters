package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.databinding.ActivityTradeUpBinding;

/**
 * TradeUpActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     24-March-2024
 *
 * @PURPOSE:
 */
public class TradeUpActivity extends AppCompatActivity {
    private ActivityTradeUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTradeUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(TradeUpActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }


}
