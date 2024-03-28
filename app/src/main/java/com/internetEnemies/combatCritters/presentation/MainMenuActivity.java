package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.data.utils.DBHelper;
import com.internetEnemies.combatCritters.databinding.ActivityMainMenuBinding;
import com.internetEnemies.combatCritters.presentation.battles.BattleActivity;
import com.internetEnemies.combatCritters.presentation.battles.BattleStartupActivity;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper.copyDatabaseToDevice(this);
        onCreateSetup();
    }

    private void onCreateSetup() {
        binding.buttonToBattleStartup.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MainMenuActivity.this, BattleStartupActivity.class);
            startActivity(intent);
        });

        binding.buttonToDeckBuilder.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MainMenuActivity.this, DeckBuilderActivity.class);
            startActivity(intent);
        });

        binding.buttonToPacks.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MainMenuActivity.this, PackOpeningActivity.class);
            startActivity(intent);
        });

        binding.buttonToMarketplace.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MainMenuActivity.this, MarketplaceActivity.class);
            startActivity(intent);
        });

        binding.buttonToTrading.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MainMenuActivity.this, TradingActivity.class);
            startActivity(intent);
        });

        binding.buttonToTradeUp.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(MainMenuActivity.this, TradeUpActivity.class);
            startActivity(intent);
        });
    }
}
