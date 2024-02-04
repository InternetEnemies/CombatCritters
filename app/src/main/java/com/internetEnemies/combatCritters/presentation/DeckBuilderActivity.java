package com.internetEnemies.combatCritters.presentation;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityDeckBuilderBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckBuilderActivity extends AppCompatActivity {

    private ActivityDeckBuilderBinding binding;
    private List<DeckDetails> decks;
    private DeckDetails selectedDeck;
    private boolean editingDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeckBuilderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        decks = new ArrayList<>();
        selectedDeck = null;
        editingDeck = false;


        setupCreateNewDeckButton();
        setupSpinner();
        setupCards();
        setupPackOpeningButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void showCreateDeckDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Deck");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String deckName = input.getText().toString();
            decks.add(new DeckDetails(1, deckName));
            setupSpinner();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }


    private void setupCreateNewDeckButton() {
        Button createNewDeckButton = findViewById(R.id.startDeckCreationButton);
        createNewDeckButton.setOnClickListener(view -> showCreateDeckDialog());
    }
    private void setupCards() {
        List<Card> cards1 = getInvCards();
        CardAdapter adapter1 = new CardAdapter(this, cards1);
        List<Card> cards2 = getInvCards();
        CardAdapter adapter2 = new CardAdapter(this, cards2);
//        binding.deckBuilderGridView.setAdapter(adapter1);
        binding.inventoryGridView.setAdapter(adapter2);
    }

    private void setupPackOpeningButton() {
        binding.buttonOpenPack.setOnClickListener(view -> {
            Intent intent = new Intent(DeckBuilderActivity.this, PackOpeningActivity.class);
            startActivity(intent);
        });
    }

    private List<DeckDetails> getDeckDetailsList() {
        List<DeckDetails> dd = new ArrayList<>();
        dd.add(new DeckDetails(1, "Deck 1"));
        dd.add(new DeckDetails(2, "Deck 2"));
        dd.add(new DeckDetails(3, "Deck 3"));
        dd.add(new DeckDetails(4, "Deck 4"));
        dd.add(new DeckDetails(5, "Deck 5"));
        dd.add(new DeckDetails(5, "Deck 5"));
        return dd;
    }

    private void setupSpinner() {
        Spinner decksDropDown = binding.decksDropDown;

        List<String> spinnerArray = new ArrayList<>();

        if(decks == null || decks.isEmpty()) {
            spinnerArray.add("No decks");
        } else {
            for(DeckDetails deck : decks) {
                spinnerArray.add(deck.toString());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decksDropDown.setAdapter(adapter);
    }


    private List<Card> getInvCards() {
        List<Card> cards = new ArrayList<>();
        CritterCard card1 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        CritterCard card2 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        ItemCard card3 = new ItemCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON,  2);
        ItemCard card4 = new ItemCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON,  2);
        CritterCard card5 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON, 22, 100, Collections.singletonList(1));
        CritterCard card6 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        CritterCard card7 = new CritterCard(1, "Card 1", "Image", 3, Card.Rarity.COMMON, 22, 100, Collections.singletonList(1));
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);

        return cards;
    }
}
