package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.Logic.IPackCatalog;
import com.internetEnemies.combatCritters.Logic.IPackOpener;
import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.Logic.PackOpener;
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
 * @PURPOSE:     Screen for displaying the results of a pack opening.
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

        GridItemAdapter<Card> adapter = new GridItemAdapter<>(CardRenderer.getRenderers(pulledCards, this));
        binding.cardsGridView.setAdapter(adapter);

        binding.buttonBackToDeckBuilder.setOnClickListener(view -> {
            Intent intent = new Intent(CardsOpenedActivity.this, DeckBuilderActivity.class);
            startActivity(intent);
        });
    }
}
