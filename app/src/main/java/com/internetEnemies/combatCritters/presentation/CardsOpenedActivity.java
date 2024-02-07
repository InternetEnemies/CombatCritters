package com.internetEnemies.combatCritters.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.Logic.PackCatalog;
import com.internetEnemies.combatCritters.Logic.PackOpener;
import com.internetEnemies.combatCritters.databinding.ActivityCardsOpenedBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public class CardsOpenedActivity extends AppCompatActivity {

    private ActivityCardsOpenedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardsOpenedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.buttonBackToDeckBuilder.setOnClickListener(view -> {
            Intent intent = new Intent(CardsOpenedActivity.this, DeckBuilderActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        pullCards();
    }

    private void pullCards() {
        PackCatalog packCatalog = new PackCatalog();
        PackOpener packOpener = new PackOpener();
        List<Pack> packs = packCatalog.getListOfPacks();

        if(packs.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No packs to open", Toast.LENGTH_SHORT).show();
        }
        else {
            List<Card> pulledCards = packOpener.openPack(packs.get(0));
            CardWithoutQuantityAdapter adapter = new CardWithoutQuantityAdapter(this, pulledCards);
            binding.cardsGridView.setAdapter(adapter);
        }
    }
}
