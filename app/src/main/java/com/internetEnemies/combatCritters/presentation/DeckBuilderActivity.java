package com.internetEnemies.combatCritters.presentation;

import android.app.AlertDialog;
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
import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckBuilderActivity extends AppCompatActivity {

    private ActivityDeckBuilderBinding binding;
    private DeckDetails selectedDeck;
    private Card selectedInventoryCard;
    private int selectedInventoryCardPosition;

    private Card selectedDeckCard;
    private int selectedDeckCardPosition;
    private CardWithoutQuantityAdapter cardAdapterBuilder;
    private CardWithQuantityAdapter cardAdapterInventory;
    private ArrayAdapter<Object> spinnerAdapter;
    private boolean showAllCards = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeckBuilderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        selectedInventoryCard = null;
        selectedInventoryCardPosition = -1;

        selectedDeckCard = null;
        selectedDeckCardPosition = -1;

        onCreateSetup();
    }

    private void onCreateSetup() {
        refreshInventory();
        setBuilderCardAdapter(new ArrayList<>());


        GridFragment<Card> inventoryFragment = GridFragment.newInstance();
        inventoryFragment.setCardAdapter(cardAdapterInventory);
        getSupportFragmentManager().beginTransaction().replace(R.id.inventoryFragmentContainer, inventoryFragment).commit();



        GridFragment<Card> builderFragment = GridFragment.newInstance();
        builderFragment.setCardAdapter(cardAdapterBuilder);
        getSupportFragmentManager().beginTransaction().replace(R.id.builderFragmentContainer, builderFragment).commit();

//        addToDeckButtonSetup();
//        RemoveFromDeckButtonSetup();
//        createNewDeckButtonSetup();
//        decksSpinnerSetup();
//        packOpeningButtonSetup();
////        cardSelectSetup();
////        cardSelect2Setup();
//        setSelectedDeck();
//        refreshInventory();
//        refreshDeckBuilder();
//        filterBySetup();
    }

    private void setSelectedDeck() {
        Object firstItem = spinnerAdapter.getItem(0);
        if(firstItem instanceof String) {
            selectedDeck = null;
        }
        else {
            selectedDeck = (DeckDetails)firstItem;
        }
    }



    private void setBuilderCardAdapter(List<Card> cards) {
        cardAdapterBuilder = new CardWithoutQuantityAdapter(this, cards);
//        binding.deckBuilderGridView.setAdapter(cardAdapterBuilder);
    }

    private void refreshDeckBuilder() {
        if(selectedDeck != null) {
            DeckManager deckManager = new DeckManager();
            DeckBuilder deckBuilder = deckManager.getBuilder(selectedDeck);
            if (deckBuilder != null) {
                List<Card> updatedCards = deckBuilder.getCards();
                setBuilderCardAdapter(updatedCards);
            }
        }
        else {
            setBuilderCardAdapter(new ArrayList<>());
        }
    }




    private void addToDeckButtonSetup() {
        Button addToDeckButton = findViewById(R.id.addToDeckButton);
        addToDeckButton.setOnClickListener(view -> addCardToDeck());
    }

    private void addCardToDeck() {
        if (selectedInventoryCard == null) {
            Toast.makeText(getApplicationContext(), "No card selected", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedDeck == null) {
            Toast.makeText(getApplicationContext(), "No deck selected", Toast.LENGTH_SHORT).show();
            return;
        }

        DeckManager deckManager = new DeckManager();
        DeckBuilder deckBuilder = deckManager.getBuilder(selectedDeck);
        if (deckBuilder == null) {
            Toast.makeText(getApplicationContext(), "Deck builder not found", Toast.LENGTH_SHORT).show();
            return;
        }

        //add card
        deckBuilder.addCard(selectedInventoryCard);
        // check validity
        DeckValidity deckValid = deckBuilder.validate();
        if(!deckValid.isValid()){
            Toast.makeText(getApplicationContext(), "Deck is not valid!",Toast.LENGTH_SHORT).show();
            for(String issue : deckValid.getIssues()) {
                Toast.makeText(getApplicationContext(), issue,Toast.LENGTH_SHORT).show();
            }
        }

        refreshDeckBuilder();
        selectedInventoryCard = null;
        selectedInventoryCardPosition = -1;
        cardAdapterInventory.clearSelection();
    }

    private void RemoveFromDeckButtonSetup(){
        //should have another id
        Button removeFromDeckButton = findViewById(R.id.removeCardFromDeckButton);
        removeFromDeckButton.setOnClickListener(view -> removeCardFromDeck());
    }

    private void removeCardFromDeck(){
        if (selectedDeckCard == null) {
            Toast.makeText(getApplicationContext(), "No card selected", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedDeck == null) {
            Toast.makeText(getApplicationContext(), "No deck selected", Toast.LENGTH_SHORT).show();
            return;
        }

        DeckManager deckManager = new DeckManager();
        DeckBuilder deckBuilder = deckManager.getBuilder(selectedDeck);
        if (deckBuilder == null) {
            Toast.makeText(getApplicationContext(), "Deck builder not found", Toast.LENGTH_SHORT).show();
            return;
        }

        int indexOfCard = deckBuilder.getCards().indexOf(selectedDeckCard);
        deckBuilder.removeCard(indexOfCard);
        refreshDeckBuilder();
        selectedDeckCard = null;
        selectedDeckCardPosition = -1;
        cardAdapterBuilder.clearSelection();
    }



    private void createNewDeckButtonSetup() {
        Button createNewDeckButton = findViewById(R.id.startDeckCreationButton);
        createNewDeckButton.setOnClickListener(view -> showCreateDeckDialog());
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
                DeckDetails newDeck = deckManager.createDeck(deckName);

                decksSpinnerRefresh();
                setSelectedDeck();

                int deckPositionInSpinner = spinnerAdapter.getPosition(newDeck);
                binding.decksDropDown.setSelection(deckPositionInSpinner);
                selectedDeck = newDeck;
                refreshDeckBuilder();
            }
            catch(IllegalArgumentException e) {
                Toast.makeText(getApplicationContext(), "Deck must have a name!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }






//
//    private void cardSelectSetup() {
//        GridView gv = findViewById(R.id.inventoryGridView);
//        gv.setOnItemClickListener((parent, view, position, id) -> {
//            // Highlight card if no card was previously selected OR
//            // if the previously selected card is different than the current selection
//            if (selectedInventoryCardPosition == -1 || selectedInventoryCardPosition != position) {
//                selectedInventoryCard = cardAdapterInventory.getItem(position);
//                selectedInventoryCardPosition = position;
//                cardAdapterInventory.setSelectedCard(selectedInventoryCardPosition);
//            }
//            // The currently selected card is the same card as the previously selected card.
//            // Remove highlight
//            else {
//                selectedInventoryCard = null;
//                selectedInventoryCardPosition = -1;
//                cardAdapterInventory.setSelectedCard(selectedInventoryCardPosition);
//
//            }
//            cardAdapterInventory.notifyDataSetChanged();
//        });
//
//    }
//
//    private void cardSelect2Setup() {
//        GridView gv = findViewById(R.id.deckBuilderGridView);
//        gv.setOnItemClickListener((parent, view, position, id) -> {
//            // Highlight card if no card was previously selected OR
//            // if the previously selected card is different than the current selection
//            if (selectedDeckCardPosition == -1 || selectedDeckCardPosition != position) {
//                selectedDeckCard = cardAdapterBuilder.getItem(position);
//                selectedDeckCardPosition = position;
//                cardAdapterBuilder.setSelectedCard(selectedDeckCardPosition);
//            }
//            // The currently selected card is the same card as the previously selected card.
//            // Remove highlight
//            else {
//                selectedDeckCard = null;
//                selectedDeckCardPosition = -1;
//                cardAdapterBuilder.setSelectedCard(selectedDeckCardPosition);
//
//            }
//            cardAdapterBuilder.notifyDataSetChanged();
//        });
//
//    }

    private void setInventoryCardAdapter(Map<Card, Integer> cardMap) {
        cardAdapterInventory = new CardWithQuantityAdapter(this, cardMap);
//        binding.inventoryGridView.setAdapter(cardAdapterInventory);
    }

    private void refreshInventory() {
        CardCatalog cardCatalog = new CardCatalog();
        if (showAllCards) {
            setInventoryCardAdapter(cardCatalog.getAll());
        } else {
            setInventoryCardAdapter(cardCatalog.getOwned());
        }
        cardAdapterInventory.notifyDataSetChanged();
    }

    private void filterBySetup() {
        binding.filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showAllCards = true;
                        break;
                    case 1:
                        showAllCards = false;
                        break;
                }
                refreshInventory();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                selectedDeckCard = null;
                selectedDeckCardPosition = -1;
                if (selectedItem instanceof DeckDetails) {
                    selectedDeck = (DeckDetails) selectedItem;
                    refreshDeckBuilder();
                } else if (selectedItem instanceof String) {
                    selectedDeck = null;
                    refreshDeckBuilder();
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
