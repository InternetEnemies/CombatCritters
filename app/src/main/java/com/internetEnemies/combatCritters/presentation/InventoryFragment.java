package com.internetEnemies.combatCritters.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.presentation.renderable.CardStackRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryFragment extends Fragment{
    private ItemGridFragment<ItemStack<Card>> gridFrag; //Watch out
    private InventoryViewModel inventoryViewModel;


    public InventoryFragment() {
        super();
    }
    public static InventoryFragment newInstance() {return new InventoryFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inventoryViewModel = new ViewModelProvider(requireActivity()).get(InventoryViewModel.class);

        //create card grid
        if(gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>(),
                    inventoryViewModel::setSelectedCard,
                    idx -> idx == inventoryViewModel.getSelectedIdx(),
                    3
            );
            getChildFragmentManager().beginTransaction().replace(R.id.gridFragmentContainer, gridFrag).commit();
        }
        inventoryViewModel.addSelectListener(i -> gridFrag.notifyDataSetChanged());

        //set listeners for filter change
        inventoryViewModel.getCardOrder().observe(this.getViewLifecycleOwner(),cardOrder -> this.refreshInventory());
        inventoryViewModel.getShowAll().observe(this.getViewLifecycleOwner(),s -> this.refreshInventory());
        inventoryViewModel.getRarity().observe(this.getViewLifecycleOwner(), s -> this.refreshInventory());

        //setups for filter/order/showall/sellButton
        setupFilterSpinner(view);
        setupOrderSpinner(view);
        setupShowAllToggle(view);
        setupSellButton(view);
        //init inventory
        refreshInventory();
    }

    private void setupFilterSpinner(View view) {
        Spinner filterSpinner = view.findViewById(R.id.filterSpinner);
        Context context = getContext();

        if(context != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.filter, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            filterSpinner.setAdapter(adapter);

            filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    inventoryViewModel.setRarity(Objects.requireNonNull(adapter.getItem(position)).toString()); // set filter based on selected
                    refreshInventory();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //Do Nothing
                }
            });
        }
    }

    private void setupOrderSpinner(View view) {
        Spinner orderSpinner = view.findViewById(R.id.orderSpinner);
        Context context = getContext();

        if(context != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.sort, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            orderSpinner.setAdapter(adapter);

            orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    inventoryViewModel.setCardOrder(Objects.requireNonNull(adapter.getItem(position)).toString());// set order based on selected
                    refreshInventory();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //Do Nothing
                }
            });
        }
    }

    private void setupShowAllToggle(View view){
        //suppress here since we don't need to worry about compatibility
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch showAllToggle = view.findViewById(R.id.showall_toggle);
        Context context = getContext();
        if(context != null) {
            showAllToggle.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> inventoryViewModel.getShowAll().setValue(isChecked) // set showall based on switch state
            );
        }
    }


    private void refreshInventory() {
        inventoryViewModel.clearSelection();
        List<ItemStack<Card>> cards = inventoryViewModel.getCards();

        gridFrag.updateItems(CardStackRenderer.getRenderers(cards,this.getContext()));

    }

    private void setupSellButton(View view) {
        Button sellButton = view.findViewById(R.id.sellButton);
        sellButton.setOnClickListener(v -> {
            ItemStack<Card> selectedCardStack = null;
            try {
                selectedCardStack = inventoryViewModel.getSelectedCard();
            } catch (UIException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if(selectedCardStack.getAmount() == 0)
                Toast.makeText(getContext(), "Card not owned", Toast.LENGTH_SHORT).show();
            else
                showCardDeconstructorPopupFragment(selectedCardStack);

        });
    }

    private void showCardDeconstructorPopupFragment(ItemStack<Card> cardStack) {
        CardDeconstructorPopupFragment popupFragment = CardDeconstructorPopupFragment.newInstance(cardStack);
        popupFragment.setSellButtonClickListener(this::refreshInventory);
        popupFragment.show(getChildFragmentManager(), "card_deconstructor_popup");
    }
}

