package com.internetEnemies.combatCritters.presentation;

import static android.widget.GridLayout.HORIZONTAL;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityBattleStartupBinding;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.Opponent;
import com.internetEnemies.combatCritters.presentation.renderable.OpponentRenderer;

import java.util.ArrayList;
import java.util.List;

public class BattleStartupActivity extends AppCompatActivity {
    private ActivityBattleStartupBinding binding;
    private ItemAdapter<Opponent> opponentAdapter;
    private List<DeckDetails> decks;
    private IDeckManager deckManager;
    private ArrayAdapter<DeckDetails> spinnerAdapter;
    private DeckDetails selectedDeck;

    private List<Opponent> opponents; //todo remove this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleStartupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.deckManager = LogicProvider.getInstance().getDeckManager();
        this.decks = deckManager.getValidDecks();
        this.selectedDeck = null;

        fillOpponents(); //TODO remove this

        setupDeckSpinner();
        setupRecycler();

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(BattleStartupActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecycler() {
        RecyclerView recycler = binding.opponentsRecycler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(new SpacingItemDecoration(10, 5));

        opponentAdapter = new ItemAdapter<>(OpponentRenderer.getRenderers(opponents, this), this::opponentClicked, true);
        recycler.setAdapter(opponentAdapter);
    }

    private void opponentClicked(Opponent opponent) {

    }

    private void setupDeckSpinner() {
        if(!decks.isEmpty()) {
            spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, decks);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.decksSpinner.setAdapter(spinnerAdapter);

            binding.decksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedDeck = (DeckDetails) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
        else {
            binding.decksSpinner.setVisibility(View.INVISIBLE);
            binding.noDecksText.setVisibility(View.VISIBLE);
        }
    }

    //TODO remove this
    private void fillOpponents() {
        opponents = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Opponent o1 = new Opponent("Trader Norman", "trader4", "The hardest opponent there is. No one is tougher than Trader Norman.");
            opponents.add(o1);
        }
    }
}
