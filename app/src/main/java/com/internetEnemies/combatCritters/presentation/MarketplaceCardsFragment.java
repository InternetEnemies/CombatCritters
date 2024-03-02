package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MarketplaceCardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketplaceCardsFragment extends Fragment {

    private ItemGridFragment<Card> gridFrag; //Watch out
    private InventoryViewModel inventoryViewModel;
    public static InventoryFragment newInstance() {return new InventoryFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inventoryViewModel = new ViewModelProvider(requireActivity()).get(InventoryViewModel.class);

        if(gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>(),
                    inventoryViewModel::setSelectedCard,
                    idx -> idx == inventoryViewModel.getSelectedIdx()
            );
            getChildFragmentManager().beginTransaction().replace(R.id.gridFragmentContainer, gridFrag).commit();
        }
        inventoryViewModel.addSelectListener(i -> gridFrag.notifyDataSetChanged());

        setupFilterSpinner(view);
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
                    refreshInventory();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    private void refreshInventory() {
        List<Card> cards = inventoryViewModel.getCards();

        gridFrag.updateItems(CardRenderer.getRenderers(cards,this.getContext()));

    }
}