package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.PackInventoryManager;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityCardsOpenedBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.io.Serializable;
import java.util.List;

/**
 * CardsOpenedActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     Opens the pack in the bundle that is passed to it and displays the cards pulled.
 */

public class CardsOpenedActivity extends AppCompatActivity {
    private static final String ARG_KEY = "pack";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.internetEnemies.combatCritters.databinding.ActivityCardsOpenedBinding binding = ActivityCardsOpenedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Serializable serializableExtra = getIntent().getSerializableExtra(ARG_KEY);

        if(serializableExtra instanceof Pack) {
            Pack pack = (Pack) serializableExtra;

            IPackInventoryManager packInventoryManager = LogicProvider.getInstance().getPackInventoryManager();
            List<Card> pulledCards = packInventoryManager.openPack(pack);

            RecyclerView recyclerView = findViewById(R.id.cardsRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.addItemDecoration(new SpacingItemDecoration());

            ItemAdapter<Card> adapter = new ItemAdapter<>(CardRenderer.getRenderers(pulledCards, this), null, false);
            recyclerView.setAdapter(adapter);
        }


        binding.buttonToPackOpening.setOnClickListener(view -> {
            Intent intent = new Intent(CardsOpenedActivity.this, PackOpeningActivity.class);
            startActivity(intent);
        });

        binding.buttonBackToDeckBuilder.setOnClickListener(view -> {
            Intent intent = new Intent(CardsOpenedActivity.this, DeckBuilderActivity.class);
            startActivity(intent);
        });
    }
}