package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityDeckBuilderBinding;

public class DeckBuilderActivity extends AppCompatActivity {
    private ActivityDeckBuilderBinding binding;
    InventoryFragment inventoryFragment;
    BuilderFragment deckBuilderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeckBuilderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onCreateSetup();
    }

    private void onCreateSetup() {
        deckBuilderFragment = new BuilderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.builderFragmentContainer, deckBuilderFragment).commit();

        inventoryFragment = InventoryFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.inventoryFragmentContainer, inventoryFragment).commit();

        binding.buttonOpenPack.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(DeckBuilderActivity.this, PackOpeningActivity.class);
            startActivity(intent);
        });

    }
}
