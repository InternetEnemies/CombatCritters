package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.internetEnemies.combatCritters.Logic.CardCatalog;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.HashMap;

public class InventoryFragment extends Fragment implements CardGridFragment.IOnCardSelectedListener{
    private CardGridFragment gridFrag; //Watch out
    private boolean showAllCards = true;
    private SelectedCardViewModel selectedCardViewModel;

    public static InventoryFragment newInstance() {return new InventoryFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectedCardViewModel = new ViewModelProvider(requireActivity()).get(SelectedCardViewModel.class);

        selectedCardViewModel.getSelectedCard().observe(getViewLifecycleOwner(), card -> {
            Log.d("here", "here");
            if (card == null) {
                gridFrag.clearSelection(false);
            }
        });


        if(gridFrag == null) {
            gridFrag = CardGridFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.gridFragmentContainer, gridFrag).commit();
            gridFrag.setAdapter(new CardWithQuantityAdapter(getContext(), new HashMap<>()));
            gridFrag.setOnCardSelectedListener(this);
        }

        setupFilterSpinner(view);
        refreshInventory();
    }

    @Override
    public void onCardSelected(Card card) {
        selectedCardViewModel.setSelectedCard(card);
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
                    showAllCards = (position == 0);
                    refreshInventory();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    private void refreshInventory() {
        CardCatalog cardCatalog = new CardCatalog();

        if (showAllCards) {
            gridFrag.updateGridView(cardCatalog.getAll());
        } else {
            gridFrag.updateGridView(cardCatalog.getOwned());
        }
    }
}

