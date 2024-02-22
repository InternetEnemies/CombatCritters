package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.os.Bundle;
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
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InventoryFragment extends Fragment{
    private ItemGridFragment<Card> gridFrag; //Watch out
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

        if(gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>(),selectedCardViewModel);
            getChildFragmentManager().beginTransaction().replace(R.id.gridFragmentContainer, gridFrag).commit();
        }


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
        //todo change this when Catalog is updated for ItemStacks
        Map<Card, Integer> cards = showAllCards ? cardCatalog.getAll() : cardCatalog.getOwned();

        gridFrag.updateItems(getRenderers(cards.keySet()));

    }

    private List<ItemRenderer<Card>> getRenderers(Collection<Card> cards) { //todo this should take a list once ItemStacks are implemented
        //! todo note this is an exact copy of BuilderFragment but will be changed when item stacks are involved
        List<ItemRenderer<Card>> renderers = new ArrayList<>();
        for( Card card : cards ){
            renderers.add(new CardRenderer(card, this.getContext()));
        }
        return renderers;
    }
}

