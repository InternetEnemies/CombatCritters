package com.internetEnemies.combatCritters.presentation.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
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
import com.internetEnemies.combatCritters.presentation.BuilderViewModel;
import com.internetEnemies.combatCritters.presentation.InventoryViewModel;
import com.internetEnemies.combatCritters.presentation.ItemGridFragment;
import com.internetEnemies.combatCritters.presentation.UIException;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.util.ArrayList;
import java.util.List;

public class BuilderFragment extends Fragment{
    private ItemGridFragment<Card> gridFrag;
    private FragmentBuilderBinding binding;
    private IDeckManager deckManager;
    private ArrayAdapter<DeckDetails> spinnerAdapter;
    private InventoryViewModel inventoryViewModel;
    private BuilderViewModel selectedDeckCardViewModel;

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

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());

        inventoryViewModel = viewModelProvider.get(InventoryViewModel.class);
        this.selectedDeckCardViewModel = viewModelProvider.get(BuilderViewModel.class);

        if (gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>(),
                    this.selectedDeckCardViewModel::setSelectedCard,
                    idx -> idx == selectedDeckCardViewModel.getSelectedIdx()
                    );
            getChildFragmentManager().beginTransaction().replace(R.id.builderFragmentContainer, gridFrag).commit();
        }
        this.selectedDeckCardViewModel.addSelectListener(i -> this.gridFrag.notifyDataSetChanged()); // rerender on selection change

        this.selectedDeckCardViewModel.getDeckDetails().observe(this.getViewLifecycleOwner(),deckDetails -> refreshGridView()); // rerender when a different deck is selected


        binding.deleteDeckButton.setOnClickListener(v -> showDeleteDeckDialog());
        binding.addToDeckButton.setOnClickListener(v -> addCardToDeck());
        binding.startDeckCreationButton.setOnClickListener(v -> showCreateDeckDialog());
        binding.removeCardFromDeckButton.setOnClickListener(v -> removeCardFromDeck());
        deckSpinnerSetup();
    }

    private void showDeleteDeckDialog() {
        Context context = getActivity();
        if(!this.selectedDeckCardViewModel.hasSelectedDeck()) {
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
                        selectedDeckCardViewModel.clearDeckSelection();
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
        try {
            this.selectedDeckCardViewModel.removeSelectedCard();
        } catch (UIException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

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
                    selectedDeckCardViewModel.setDeck(spinnerAdapter.getItem(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    private void addCardToDeck() {

        //add card
        Card card = inventoryViewModel.getSelectedCard();
        try {
            selectedDeckCardViewModel.addCardToDeck(card);
            selectedDeckCardViewModel.clearSelection();
            // check validity
            DeckValidity deckValid = selectedDeckCardViewModel.getValidity();
            updateValidity(deckValid);
        } catch (UIException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        refreshGridView();
    }

    private void updateValidity(DeckValidity deckValid) {
        if (!deckValid.isValid()) {
            Toast.makeText(getContext(), "Deck is not valid!", Toast.LENGTH_SHORT).show();
            for (String issue : deckValid.getIssues()) {
                Toast.makeText(getContext(), issue, Toast.LENGTH_SHORT).show();
            }
        }
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
                Toast.makeText(getContext(), "Deck must have a name!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    //Refresh the gridview with the deck currently selected in the spinner
    private void refreshGridView() {
        selectedDeckCardViewModel.clearSelection();
        DeckDetails deck = getSelectedDeck();
        if(deck == null) {
            gridFrag.updateItems(new ArrayList<>());
        }
        else {
            IDeckBuilder deckBuilder = deckManager.getBuilder(deck);
            List<Card> updatedCards;
            if (deckBuilder != null) {
                updatedCards = deckBuilder.getCards();
            }
            else {
                updatedCards = new ArrayList<>();
            }
            gridFrag.updateItems(CardRenderer.getRenderers(updatedCards, this.getContext()));
        }
    }

    private void spinnerDeleteDeck(DeckDetails deckToDelete) {
        if(deckToDelete != null) {
            spinnerAdapter.remove(deckToDelete);
            spinnerAdapter.notifyDataSetChanged();
        }
    }

    private DeckDetails getSelectedDeck() {
        return this.selectedDeckCardViewModel.getDeckDetails().getValue();
    }
}
