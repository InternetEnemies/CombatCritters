package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.IPackCatalog;
import com.internetEnemies.combatCritters.Logic.IPackOpener;
import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.Logic.PackOpener;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityCardsOpenedBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.util.List;

/**
 * CardsOpenedActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     Opens the pack in the bundle that is passed to it and displays the results of
 *               opening the pack.
 */

public class CardsOpenedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.internetEnemies.combatCritters.databinding.ActivityCardsOpenedBinding binding = ActivityCardsOpenedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        IPackOpener packOpener = new PackOpener();
        Pack pack = (Pack) getIntent().getSerializableExtra("pack");
        List<Card> pulledCards = packOpener.openPack(pack);

        RecyclerView recyclerView = findViewById(R.id.cardsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new SpacingItemDecoration());

        ItemAdapter<Card> adapter = new ItemAdapter<>(CardRenderer.getRenderers(pulledCards, this), null, false);
        recyclerView.setAdapter(adapter);


        binding.buttonBackToDeckBuilder.setOnClickListener(view -> {
            Intent intent = new Intent(CardsOpenedActivity.this, DeckBuilderActivity.class);
            startActivity(intent);
        });
    }
}
