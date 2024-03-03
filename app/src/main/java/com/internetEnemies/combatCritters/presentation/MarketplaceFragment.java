package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentMarketplaceBinding;
import com.internetEnemies.combatCritters.objects.Transaction;
import com.internetEnemies.combatCritters.presentation.renderable.TransactionRenderer;

import java.util.ArrayList;
import java.util.List;

public class MarketplaceFragment extends Fragment {
    private ItemGridFragment<Transaction> gridFrag;
    private FragmentMarketplaceBinding binding;
    private MarketplaceViewModel selectedOffersViewModel;
    private MarketHandler marketHandler;

    private TabLayout tabLayout;

    public MarketplaceFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMarketplaceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());

        this.selectedOffersViewModel = viewModelProvider.get(MarketplaceViewModel.class);
        this.marketHandler = new MarketHandler();

        if (gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>(),
                    this.selectedOffersViewModel::setSelectedTransaction,
                    idx -> idx == selectedOffersViewModel.getSelectedIdx()
            );
            getChildFragmentManager().beginTransaction().replace(R.id.marketFragmentContainer, gridFrag).commit();
        }
        this.selectedOffersViewModel.addSelectListener(i -> this.gridFrag.notifyDataSetChanged()); // rerender on selection change


        this.selectedOffersViewModel.getOffers().observe(this.getViewLifecycleOwner(),deckDetails -> refreshGridView()); // rerender when a different deck is selected

        tabLayout = binding.tabLayout;

        setupTabLayout();
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

                        break;
                    case 2: // Bundles tab selected

                        break;
                    case 3: //Deals tab selected

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private List<Transaction> getOffers(int tabPosition) {
        if(tabPosition == 0) {return marketHandler.getCardOffers();}
        else {return marketHandler.getCardOffers();}
    }

    //Refresh the gridview with the offers currently selected in the tab layout
    private void refreshGridView() {
        selectedOffersViewModel.clearSelection();
        List<Transaction> updatedOffers = selectedOffersViewModel.getOffers().getValue();//getSelectedOffers();
        if(updatedOffers == null) {
            gridFrag.updateItems(new ArrayList<>());
        }
        else {
            gridFrag.updateItems(TransactionRenderer.getRenderers(updatedOffers, this.getContext()));
        }
    }

    private List<Transaction> getSelectedOffers() {
        return this.selectedOffersViewModel.getOffers().getValue();
    }
}
