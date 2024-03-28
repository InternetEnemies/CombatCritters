package com.internetEnemies.combatCritters.presentation.battles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.databinding.ActivityBattleStartupBinding;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.battles.Opponent;
import com.internetEnemies.combatCritters.presentation.ItemAdapter;
import com.internetEnemies.combatCritters.presentation.LogicProvider;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;
import com.internetEnemies.combatCritters.presentation.SpacingItemDecoration;
import com.internetEnemies.combatCritters.presentation.renderable.OpponentRenderer;

import java.util.List;


/**
 * BattleStartupActivity.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     25/March/24
 *
 * @PURPOSE:    Startup screen for battles.
 */
public class BattleStartupActivity extends AppCompatActivity {
    private ActivityBattleStartupBinding binding;
    private List<DeckDetails> decks;
    private DeckDetails selectedDeck;
    private Opponent selectedOpponent;
    private List<Opponent> opponents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleStartupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.decks = LogicProvider.getInstance().getDeckManager().getValidDecks();
        this.selectedDeck = null;
        this.selectedOpponent = null;

        opponents = LogicProvider.getInstance().getBattleRegistry().getBattles();

        setupDeckSpinner();
        setupRecycler();

        binding.enterCombatButton.setOnClickListener(v -> enterCombatButtonClicked());

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(BattleStartupActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    private void enterCombatButtonClicked() {
        if(selectedOpponent == null && selectedDeck == null) {
            Toast.makeText(this, "Must select a deck and an opponent before entering critter combat", Toast.LENGTH_SHORT).show();
        }
        else if(selectedOpponent == null) {
            Toast.makeText(this, "Must select an opponent before entering critter combat", Toast.LENGTH_SHORT).show();
        }
        else if(selectedDeck == null) {
            Toast.makeText(this, "Must select a deck before entering critter combat", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, BattleActivity.class);
            intent.putExtra("battleId", selectedOpponent.getId());
            intent.putExtra("deckDetails", selectedDeck);
            startActivity(intent);
        }
    }

    private void setupRecycler() {
        RecyclerView recycler = binding.opponentsRecycler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(new SpacingItemDecoration(10, 0));

        ItemAdapter<Opponent> opponentAdapter = new ItemAdapter<>(OpponentRenderer.getRenderers(opponents, this), this::opponentClicked, true);
        recycler.setAdapter(opponentAdapter);
    }

    private void opponentClicked(Opponent opponent) {
        if(opponent == selectedOpponent) {
            this.selectedOpponent = null;
        } else{
            this.selectedOpponent = opponent;
        }
    }

    private void setupDeckSpinner() {
        if(!decks.isEmpty()) {
            ArrayAdapter<DeckDetails> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, decks);
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
}
