package com.internetEnemies.combatCritters.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.util.ArrayList;
import java.util.List;

public class BuilderFragment extends Fragment{
    private ItemAdapter<Card> itemAdapter;
    private FragmentBuilderBinding binding;
    private IDeckManager deckManager;
    private ArrayAdapter<DeckDetails> spinnerAdapter;
    private InventoryViewModel inventoryViewModel;
    private BuilderViewModel selectedDeckCardViewModel;
    private DeckValidityAdapter deckValidityAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBuilderBinding.inflate(inflater, container, false);
        deckManager = LogicProvider.getInstance().getDeckManager();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());

        inventoryViewModel = viewModelProvider.get(InventoryViewModel.class);
        this.selectedDeckCardViewModel = viewModelProvider.get(BuilderViewModel.class);

        itemAdapter = new ItemAdapter<>(new ArrayList<>(), this::setSelectedCard, true);
        RecyclerView recyclerView = view.findViewById(R.id.builderRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.addItemDecoration(new SpacingItemDecoration());
        recyclerView.setAdapter(itemAdapter);

        this.selectedDeckCardViewModel.addDeckChangeListener(this::updateValidity);

        binding.deleteDeckButton.setOnClickListener(v -> showDeleteDeckDialog());
        binding.addToDeckButton.setOnClickListener(v -> addCardToDeck());
        binding.startDeckCreationButton.setOnClickListener(v -> showCreateDeckDialog());
        binding.removeCardFromDeckButton.setOnClickListener(v -> removeCardFromDeck());
        deckSpinnerSetup();
        deckValiditySetup();

        this.selectedDeckCardViewModel.getDeckDetails().observe(this.getViewLifecycleOwner(),deckDetails -> {
            refreshGridView();// rerender when a different deck is selected
            this.deckValidityAdapter.updateIssues(new ArrayList<>()); // reset deck validity
        });
    }

    /**
     * Set the selected item position in the view model to the currently selected item in the adapter
     * @param card Not used here but is necessary to match the callback function in ItemAdapter.
     */
    public void setSelectedCard(Card card) {
        if(itemAdapter.getSelectedItemPosition() == -1) {
            selectedDeckCardViewModel.setSelectedCard(-1);
        }
        else {
            selectedDeckCardViewModel.setSelectedCard(itemAdapter.getSelectedItemPosition());
        }
    }

    /**
     * setup the validity recycler view
     */
    private void deckValiditySetup() {
        this.deckValidityAdapter = new DeckValidityAdapter(new ArrayList<>());
        RecyclerView issues = this.requireView().findViewById(R.id.deck_issues);
        issues.setLayoutManager(new LinearLayoutManager(getActivity()));
        issues.setAdapter(this.deckValidityAdapter);
    }

    private void showDeleteDeckDialog() {
        Context context = getActivity();
        if(!this.selectedDeckCardViewModel.hasSelectedDeck()) {
            Toast.makeText(context, "No deck to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(context).setTitle("Delete deck").setMessage("Are you sure you want to delete your deck?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    Toast.makeText(context, "Deck deleted!", Toast.LENGTH_SHORT).show();
                    DeckDetails deckToDelete = getSelectedDeck();
                    deckManager.deleteDeck(deckToDelete);
                    spinnerDeleteDeck(deckToDelete);
                    spinnerAdapter.notifyDataSetChanged();
                    selectedDeckCardViewModel.clearDeckSelection();
                    refreshGridView();
                })
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss())
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
            spinnerAdapter.addAll(this.deckManager.getDecks());
        }
    }

    private void addCardToDeck() {
        //add card
        try {
            Card card = inventoryViewModel.getSelectedCard().getItem();
            selectedDeckCardViewModel.addCardToDeck(card);
            selectedDeckCardViewModel.clearSelection();
            // check validity
        } catch (UIException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        refreshGridView();
    }

    private void updateValidity(DeckValidity deckValid) {
        System.out.println("Updating validity");
        this.deckValidityAdapter.updateIssues(deckValid.getIssues());
    }

    private void showCreateDeckDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Create New Deck");

        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setId(R.id.deckIdForSys); // Assign a custom ID
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String deckName = input.getText().toString().trim();
            if (!deckName.isEmpty()) { // Make sure there is a name
                if (deckName.length() > 20) { // Make sure the name is 20 characters or less
                    Toast.makeText(getContext(), "Deck name must be 20 characters or less!", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean deckExists = false;
                    for (int i = 0; i < spinnerAdapter.getCount(); i++) {
                        DeckDetails existingDeck = spinnerAdapter.getItem(i);
                        if (existingDeck != null && existingDeck.getName().equalsIgnoreCase(deckName)) {
                            deckExists = true;
                            break;
                        }
                    }
                    if (deckExists) { // Make sure the deck name doesn't already exist
                        Toast.makeText(getContext(), "Deck name already exists!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        DeckDetails newDeck = deckManager.createDeck(deckName);
                        spinnerAdapter.add(newDeck);
                        spinnerAdapter.notifyDataSetChanged();

                        int position = spinnerAdapter.getPosition(newDeck);
                        if (position >= 0) {
                            binding.decksDropDown.setSelection(position);
                        }

                        refreshGridView();
                    }
                }
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
            itemAdapter.updateItems(new ArrayList<>());
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
            itemAdapter.clearHighlight();
            itemAdapter.updateItems(CardRenderer.getRenderers(updatedCards, this.getContext()));
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
