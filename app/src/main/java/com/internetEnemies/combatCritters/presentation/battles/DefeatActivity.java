package com.internetEnemies.combatCritters.presentation.battles;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.databinding.ActivityDefeatBinding;

public class DefeatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.internetEnemies.combatCritters.databinding.ActivityDefeatBinding binding = ActivityDefeatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBackToDeckBuilder.setOnClickListener(view -> {
            Intent intent = new Intent(DefeatActivity.this, BattleStartupActivity.class);
            startActivity(intent);
        });
    }
}
