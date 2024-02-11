package com.internetEnemies.combatCritters.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.internetEnemies.combatCritters.Logic.DeckBuilder;
import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentBuilderBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.util.ArrayList;
import java.util.List;

public class BuilderFragment extends Fragment {
    private CardGridFragment gridFrag;
    private FragmentBuilderBinding binding;
    private DeckDetails selectedDeck;
    private DeckManager deckManager;
    private ArrayAdapter<Object> spinnerAdapter;

    public BuilderFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBuilderBinding.inflate(inflater, container, false);
        deckManager = new DeckManager();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (gridFrag == null) {
            gridFrag = CardGridFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.builderFragmentContainer, gridFrag).commit();
            gridFrag.setAdapter(new CardWithoutQuantityAdapter(getContext(), new ArrayList<>()));
        }

        binding.addToDeckButton.setOnClickListener(v -> addCardToDeck());
        binding.startDeckCreationButton.setOnClickListener(v -> showCreateDeckDialog());
        binding.removeCardFromDeckButton.setOnClickListener(v -> removeCardFromDeck());
        deckSpinnerSetup();
    }

    private void removeCardFromDeck(){
        Context context = getContext();
        Card selectedCard = gridFrag.getSelectedCard();
        if (selectedCard == null) {
            Toast.makeText(context, "No card selected", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedDeck == null) {
            Toast.makeText(context, "No deck selected", Toast.LENGTH_SHORT).show();
            return;
        }

        DeckBuilder deckBuilder = deckManager.getBuilder(selectedDeck);
        if (deckBuilder == null) {
            Toast.makeText(context, "Deck builder not found", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Card> cardsInDeck = deckBuilder.getCards();
        int indexOfCard = cardsInDeck.indexOf(selectedCard);
        if(indexOfCard < 0) {
            Toast.makeText(context, "Card not found", Toast.LENGTH_SHORT).show();
            return;
        }
        deckBuilder.removeCard(indexOfCard);
        gridFrag.clearSelection();
        refreshDeckBuilder();
    }

    private void deckSpinnerSetup() {
        Context context = getContext();
        if(context != null) {
            spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, new ArrayList<>());
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.decksDropDown.setAdapter(spinnerAdapter);
            binding.decksDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object selectedItem = parent.getItemAtPosition(position);
                    if (selectedItem instanceof DeckDetails) {
                        selectedDeck = (DeckDetails) selectedItem;
                    } else {
                        selectedDeck = null;
                    }
                    gridFrag.clearSelection();
                    refreshDeckBuilder();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            decksSpinnerRefresh();
        }
    }

    //Refreshes deck gridview with cards pulled from DeckBuilder
    private void refreshDeckBuilder() {
        if (selectedDeck != null) {
            DeckBuilder deckBuilder = deckManager.getBuilder(selectedDeck);
            List<Card> updatedCards;
            if (deckBuilder != null) {
                updatedCards = deckBuilder.getCards();
            }
            else {
                updatedCards = new ArrayList<>();
            }
            gridFrag.updateGridView(updatedCards);
        }
        else {
            gridFrag.updateGridView(new ArrayList<>());
        }
    }

    private void addCardToDeck() {
        Context context = getContext();

        //TODO: find a better way to do this
        DeckBuilderActivity activity = (DeckBuilderActivity)getActivity();
        Card card = activity.getSelectedInventoryCard();

        if(card == null) {
            Toast.makeText(context, "No card selected", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedDeck == null) {
            Toast.makeText(context, "No deck selected", Toast.LENGTH_SHORT).show();
            return;
        }

        DeckBuilder deckBuilder = deckManager.getBuilder(selectedDeck);
        if (deckBuilder == null) {
            Toast.makeText(context, "Deck builder not found", Toast.LENGTH_SHORT).show();
            return;
        }

        //add card
        deckBuilder.addCard(card);
        // check validity
        DeckValidity deckValid = deckBuilder.validate();
        if (!deckValid.isValid()) {
            Toast.makeText(context, "Deck is not valid!", Toast.LENGTH_SHORT).show();
            for (String issue : deckValid.getIssues()) {
                Toast.makeText(context, issue, Toast.LENGTH_SHORT).show();
            }
        }

        refreshDeckBuilder();

    }
    private void showCreateDeckDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Create New Deck");

        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String deckName = input.getText().toString().trim();
            if (!deckName.isEmpty()) {
                DeckDetails newDeck = deckManager.createDeck(deckName);
                decksSpinnerRefresh();

                int position = spinnerAdapter.getPosition(newDeck);
                if (position >= 0) {
                    binding.decksDropDown.setSelection(position);
                    selectedDeck = newDeck;
                    refreshDeckBuilder();
                }
            }
            else {
                Toast.makeText(getContext(), "Deck must have a name!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }


    private void decksSpinnerRefresh() {
        List<DeckDetails> decks = deckManager.getDecks();
        spinnerAdapter.clear();
        spinnerAdapter.addAll(decks);
        spinnerAdapter.notifyDataSetChanged();
    }

}
