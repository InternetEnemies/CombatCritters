package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.Logic.TempMarketHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentMarketplaceBinding;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.presentation.renderable.TransactionRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * MarketplaceFragment.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Fragment for displaying transaction offers.
 */
public class MarketplaceFragment extends Fragment {
    private ItemGridFragment<MarketTransaction> gridFrag;
    private FragmentMarketplaceBinding binding;
    private MarketplaceViewModel selectedOffersViewModel;
    private IMarketHandler marketHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMarketplaceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());

        this.selectedOffersViewModel = viewModelProvider.get(MarketplaceViewModel.class);
        this.marketHandler = new TempMarketHandler(); //TODO change this back to MarketHandler

        if (gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>(),
                    this.selectedOffersViewModel::setSelectedTransaction,
                    idx -> idx == selectedOffersViewModel.getSelectedPositionAsInt()
            );
            getChildFragmentManager().beginTransaction().replace(R.id.marketFragmentGridContainer, gridFrag).commit();
        }

        this.selectedOffersViewModel.addSelectListener(i -> this.gridFrag.notifyDataSetChanged()); // re-render on selection change
        this.selectedOffersViewModel.getOffers().observe(this.getViewLifecycleOwner(),deckDetails -> refreshGridView());

        setupTabLayout();
        selectedOffersViewModel.setOffers(marketHandler.getCardOffers());   //Needed for when the page is initially navigated to.
    }

    private void setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition(); // Position of selected tab
                switch (position) {
                    case 0: // Cards tab selected
                        selectedOffersViewModel.setOffers(marketHandler.getCardOffers());
                        break;
                    case 1: // Packs tab selected
                        selectedOffersViewModel.setOffers(marketHandler.getPackOffers());
                        break;
                    case 2: // Bundles tab selected
                        selectedOffersViewModel.setOffers(marketHandler.getBundleOffers());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    /**
     * Refresh the gridview with the offers currently selected in the tab layout.
     */
    private void refreshGridView() {
        selectedOffersViewModel.clearSelection();
        List<MarketTransaction> updatedOffers = selectedOffersViewModel.getOffers().getValue();
        if(updatedOffers == null) {
            gridFrag.updateItems(new ArrayList<>());
        }
        else {
            gridFrag.updateItems(TransactionRenderer.getRenderers(updatedOffers, this.getContext()));
        }
    }
}
