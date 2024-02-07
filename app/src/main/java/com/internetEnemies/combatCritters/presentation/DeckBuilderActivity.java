package com.internetEnemies.combatCritters.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

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
    private List<Card> cardsInInventory;
    private List<Card> cardsInBuilder;
    private Card selectedCard;
    private int selectedCardPosition;
    private DeckBuilderCardAdapter cardAdapterBuilder;
    private CardAdapter cardAdapterInventory;
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
    }

    private void onCreateSetup() {
        builderInventoryAdaptersSetup(); //Bind CardAdapters to both GridViews, add sample cards
        addCardsToInventory();           //Will most likely be deleted once database is ready
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
            String deckName = input.getText().toString();
            decks.add(new DeckDetails(decks.size(), deckName));
            decksSpinnerRefresh();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void createNewDeckButtonSetup() {
        Button createNewDeckButton = findViewById(R.id.startDeckCreationButton);
        createNewDeckButton.setOnClickListener(view -> showCreateDeckDialog());
    }

    private void builderInventoryAdaptersSetup() {
        cardsInInventory = new ArrayList<>();
        cardAdapterInventory = new CardAdapter(this, cardsInInventory);
        binding.inventoryGridView.setAdapter(cardAdapterInventory);

        cardsInBuilder = new ArrayList<>();
        cardAdapterBuilder = new DeckBuilderCardAdapter(this, cardsInBuilder);
        binding.deckBuilderGridView.setAdapter(cardAdapterBuilder);
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
        decksSpinnerRefresh();
    }

    private void decksSpinnerRefresh() {
        if (decks == null || decks.isEmpty()) {
            spinnerAdapter.clear();
            spinnerAdapter.add("No decks");
        } else {
            spinnerAdapter.clear();
            spinnerAdapter.addAll(decks);
        }
        spinnerAdapter.notifyDataSetChanged();
    }
    
    private void addCardsToInventory() {
        cardsInInventory.addAll(getInvCards());
        cardAdapterInventory.notifyDataSetChanged();
    }
    private List<Card> getInvCards() {
        List<Card> cards = new ArrayList<>();
        CritterCard card1 = new CritterCard(1, "Card 1", "card_id_37", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        CritterCard card2 = new CritterCard(2, "Card 1", "card_id_37", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        ItemCard card3 = new ItemCard(3, "Card 1", "card_id_37", 3, Card.Rarity.COMMON,  2);
        ItemCard card4 = new ItemCard(14, "Card 1", "card_id_37", 3, Card.Rarity.COMMON,  2);
        CritterCard card5 = new CritterCard(15, "Card 1", "card_id_37", 3, Card.Rarity.COMMON, 22, 100, Collections.singletonList(1));
        CritterCard card6 = new CritterCard(16, "Card 1", "card_id_37", 3, Card.Rarity.RARE, 22, 100, Collections.singletonList(1));
        CritterCard card7 = new CritterCard(17, "Card 1", "card_id_37", 3, Card.Rarity.COMMON, 22, 100, Collections.singletonList(1));
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
