package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityDeckBuilderBinding;

public class DeckBuilderActivity extends AppCompatActivity {
    private ActivityDeckBuilderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeckBuilderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onCreateSetup();
    }

    private void onCreateSetup() {
        BuilderFragment deckBuilderFragment = new BuilderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.builderFragmentContainer, deckBuilderFragment).commit();

        InventoryFragment inventoryFragment = InventoryFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.inventoryFragmentContainer, inventoryFragment).commit();

        binding.buttonMainMenu.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(DeckBuilderActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }
}
