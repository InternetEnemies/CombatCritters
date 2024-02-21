package com.internetEnemies.combatCritters.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.internetEnemies.combatCritters.Logic.DeckManager;
import com.internetEnemies.combatCritters.Logic.IDeckBuilder;
import com.internetEnemies.combatCritters.Logic.IDeckManager;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentBuilderBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.DeckValidity;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.ArrayList;
import java.util.List;

public class BuilderFragment extends Fragment implements CardGridFragment.ICardSelectionListener{
    private ItemGridFragment<Card> gridFrag;
    private FragmentBuilderBinding binding;
    private IDeckManager deckManager;
    private ArrayAdapter<DeckDetails> spinnerAdapter;
    private Card selectedDeckCard = null;
    private SelectedCardViewModel selectedCardViewModel;

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

        selectedCardViewModel = new ViewModelProvider(requireActivity()).get(SelectedCardViewModel.class);

        if (gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>());
            getChildFragmentManager().beginTransaction().replace(R.id.builderFragmentContainer, gridFrag).commit();
            gridFrag.addSelectListener(this::onCardSelected);
        }

        binding.deleteDeckButton.setOnClickListener(v -> showDeleteDeckDialog());
        binding.addToDeckButton.setOnClickListener(v -> addCardToDeck());
        binding.startDeckCreationButton.setOnClickListener(v -> showCreateDeckDialog());
        binding.removeCardFromDeckButton.setOnClickListener(v -> removeCardFromDeck());
        deckSpinnerSetup();
    }

    @Override
    public void onCardSelected(Card card) {
        selectedDeckCard = card;
    }

    private void showDeleteDeckDialog() {
        Context context = getActivity();
        if(getSelectedDeck() == null) {
            Toast.makeText(context, "No deck to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(context).setTitle("Delete deck").setMessage("Are you sure you want to delete your deck?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Deck deleted!", Toast.LENGTH_SHORT).show();
                        DeckDetails deckToDelete = getSelectedDeck();
                        deckManager.deleteDeck(deckToDelete);
                        spinnerDeleteDeck(deckToDelete);
                        spinnerAdapter.notifyDataSetChanged();
                        refreshGridView();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }


    private void removeCardFromDeck(){
        Context context = getContext();
        if (selectedDeckCard == null) {
            Toast.makeText(context, "No card selected", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getSelectedDeck() == null) {
            Toast.makeText(context, "No deck selected", Toast.LENGTH_SHORT).show();
            return;
        }

        IDeckBuilder deckBuilder = deckManager.getBuilder(getSelectedDeck());
        if (deckBuilder == null) {
            Toast.makeText(context, "Deck builder not found", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Card> cardsInDeck = deckBuilder.getCards();
        int indexOfCard = cardsInDeck.indexOf(selectedDeckCard);
        if(indexOfCard < 0) {
            Toast.makeText(context, "Card not found", Toast.LENGTH_SHORT).show();
            return;
        }

        deckBuilder.removeCard(indexOfCard);
        refreshGridView();
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
                    refreshGridView();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    private void addCardToDeck() {
        Context context = getContext();

        if (getSelectedDeck() == null) {
            Toast.makeText(context, "No deck selected", Toast.LENGTH_SHORT).show();
            return;
        }

         Card card = selectedCardViewModel.getSelectedCard().getValue();
         selectedCardViewModel.setSelectedCard(null);

        if(card == null) {
            Toast.makeText(context, "No card selected", Toast.LENGTH_SHORT).show();
            return;
        }

        IDeckBuilder deckBuilder = deckManager.getBuilder(getSelectedDeck());
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
        refreshGridView();
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
                spinnerAdapter.add(newDeck);
                spinnerAdapter.notifyDataSetChanged();

                int position = spinnerAdapter.getPosition(newDeck);
                if (position >= 0) {
                    binding.decksDropDown.setSelection(position);
                }

                refreshGridView();
            }
            else {
                //TODO: have something in the logic layer determine if a deck name is valid
                Toast.makeText(getContext(), "Deck must have a name!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    //Refresh the gridview with the deck currently selected in the spinner
    private void refreshGridView() {
        //gridFrag.clearSelection(true);//todo
        if(getSelectedDeck() == null) {
            gridFrag.updateItems(new ArrayList<>());
        }
        else {
            IDeckBuilder deckBuilder = deckManager.getBuilder(getSelectedDeck());
            List<Card> updatedCards;
            if (deckBuilder != null) {
                updatedCards = deckBuilder.getCards();
            }
            else {
                updatedCards = new ArrayList<>();
            }
            gridFrag.updateItems(getCardRenderers(updatedCards));
        }
    }

    private List<ItemRenderer<Card>> getCardRenderers(List<Card> cards) {
        List<ItemRenderer<Card>> renderers = new ArrayList<>();
        for( Card card : cards ){
            renderers.add(new CardRenderer(card, this.getContext()));
        }
        return renderers;
    }

    private void spinnerDeleteDeck(DeckDetails deckToDelete) {
        if(deckToDelete != null) {
            spinnerAdapter.remove(deckToDelete);
            spinnerAdapter.notifyDataSetChanged();
        }
    }

    private DeckDetails getSelectedDeck() {
        int selectedPosition = binding.decksDropDown.getSelectedItemPosition();
        if(selectedPosition != AdapterView.INVALID_POSITION && selectedPosition < spinnerAdapter.getCount()) {
            return spinnerAdapter.getItem(selectedPosition);
        }
        else {
            return null;
        }
    }
}
