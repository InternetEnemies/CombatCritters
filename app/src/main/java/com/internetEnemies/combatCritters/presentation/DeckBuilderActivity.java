package com.internetEnemies.combatCritters.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityDeckBuilderBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckBuilderActivity extends AppCompatActivity {

    private ActivityDeckBuilderBinding binding;
    private List<DeckDetails> decks;
    private DeckDetails selectedDeck;
    private List<Card> cardsInBuilder;
    private Card selectedCard;
    private int selectedCardPosition;
    private CardWithoutQuantityAdapter cardAdapterBuilder;
    private CardWithQuantityAdapter cardAdapterInventory;
    private ArrayAdapter<Object> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeckBuilderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        decks = new ArrayList<>();
        selectedCard = null;
        selectedCardPosition = -1;

        onCreateSetup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshInventory();
    }

    private void onCreateSetup() {
        setInventoryCardAdapter(new HashMap<Card, Integer>());
        builderCardAdapterSetup();
        addToDeckButtonSetup();
        createNewDeckButtonSetup();
        deleteDeckButtonSetup();
        decksSpinnerSetup();
        packOpeningButtonSetup();
        cardSelectSetup();
    }

    private void addToDeckButtonSetup() {
        Button addToDeckButton = findViewById(R.id.addToDeckButton);
        addToDeckButton.setOnClickListener(view -> addCard());
    }

    private void addCard() {
        cardAdapterInventory.clearSelection();
        Spinner decksDropDown = findViewById(R.id.decksDropDown);

        if(decksDropDown.getSelectedItem() instanceof String) {
            Toast.makeText(getApplicationContext(), "No deck selected", Toast.LENGTH_SHORT).show();
        }
        else if(selectedCard == null) {
            Toast.makeText(getApplicationContext(), "No card selected", Toast.LENGTH_SHORT).show();
        }
        else {
//                DeckDetails selectedDeck = (DeckDetails)decksDropDown.getSelectedItem();
//                cardsInBuilder.clear();
            cardsInBuilder.add(selectedCard);
            cardAdapterBuilder.notifyDataSetChanged();
            selectedCard = null;
            selectedCardPosition = -1;
        }
    }

    private void deleteDeckButtonSetup() {
        Button deleteDeckButton = findViewById(R.id.deleteDeckButton);
        deleteDeckButton.setOnClickListener(view -> showConfirmationDialog());
    }

    private void showConfirmationDialog() {
        Spinner decksDropDown = findViewById(R.id.decksDropDown);
        if(decksDropDown.getSelectedItem() instanceof String) {
            Toast.makeText(getApplicationContext(), "No deck to delete", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete Deck");
            builder.setMessage("Are you sure you want to delete this deck?");

            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void cardSelectSetup() {
        GridView gv = findViewById(R.id.inventoryGridView);
        gv.setOnItemClickListener((parent, view, position, id) -> {
            // Highlight card if no card was previously selected OR
            // if the previously selected card is different than the current selection
            if (selectedCardPosition == -1 || selectedCardPosition != position) {
                selectedCard = cardAdapterInventory.getItem(position);
                selectedCardPosition = position;
                cardAdapterInventory.setSelectedCard(selectedCardPosition);
            }
            // The currently selected card is the same card as the previously selected card.
            // Remove highlight
            else {
                selectedCard = null;
                selectedCardPosition = -1;
                cardAdapterInventory.setSelectedCard(selectedCardPosition);
            }
            cardAdapterInventory.notifyDataSetChanged();
        });

    }

    private void showCreateDeckDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Deck");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            try {
                String deckName = input.getText().toString();
                DeckManager deckManager = new DeckManager();
                deckManager.createDeck(deckName);
//                decks.add(new DeckDetails(decks.size(), deckName));
                decksSpinnerRefresh();
            }
            catch(IllegalArgumentException e) {
//                dialog.cancel();
                Toast.makeText(getApplicationContext(), "Deck must have a name", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void createNewDeckButtonSetup() {
        Button createNewDeckButton = findViewById(R.id.startDeckCreationButton);
        createNewDeckButton.setOnClickListener(view -> showCreateDeckDialog());
    }

    private void builderCardAdapterSetup() {
        cardsInBuilder = new ArrayList<>();
        cardAdapterBuilder = new CardWithoutQuantityAdapter(this, cardsInBuilder);
        binding.deckBuilderGridView.setAdapter(cardAdapterBuilder);
    }

    private void setInventoryCardAdapter(Map<Card, Integer> cardMap) {
        cardAdapterInventory = new CardWithQuantityAdapter(this, cardMap);
        binding.inventoryGridView.setAdapter(cardAdapterInventory);
    }

    private void refreshInventory() {
        CardCatalog cardCatalog = new CardCatalog();
        setInventoryCardAdapter(cardCatalog.getOwned());
        cardAdapterInventory.notifyDataSetChanged();
    }

    private void packOpeningButtonSetup() {
        binding.buttonOpenPack.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(DeckBuilderActivity.this, PackOpeningActivity.class);
            startActivity(intent);
        });
    }

    private void decksSpinnerSetup() {
        Spinner decksDropDown = binding.decksDropDown;
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decksDropDown.setAdapter(spinnerAdapter);

        decksDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object selectedItem = parent.getItemAtPosition(position);
                if (selectedItem instanceof DeckDetails) {
                    selectedDeck = (DeckDetails) selectedItem;
                } else if (selectedItem instanceof String) {
                    selectedDeck = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        decksSpinnerRefresh();
    }

    private void decksSpinnerRefresh() {
        DeckManager deckManager = new DeckManager();
        if (deckManager.getDecks().isEmpty()) {
            spinnerAdapter.clear();
            spinnerAdapter.add("No decks");
        } else {
            spinnerAdapter.clear();
            spinnerAdapter.addAll(deckManager.getDecks());
        }
        spinnerAdapter.notifyDataSetChanged();
    }
}
