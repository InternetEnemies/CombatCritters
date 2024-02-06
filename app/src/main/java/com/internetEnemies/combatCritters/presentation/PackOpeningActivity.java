package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.databinding.PackOpeningBinding;

public class PackOpeningActivity extends AppCompatActivity {

    private PackOpeningBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PackOpeningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBackToDeckBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.buttonCardsOpened.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(PackOpeningActivity.this, CardsOpenedActivity.class);
                 startActivity(intent);
            }
        });
    }
}
