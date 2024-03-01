package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.databinding.ActivityMarketplaceBinding;

public class MarketplaceActivity extends AppCompatActivity {
    private ActivityMarketplaceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMarketplaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onCreateSetup();
    }

    private void onCreateSetup() {
        binding.buttonMainMenu.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MarketplaceActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }
}
