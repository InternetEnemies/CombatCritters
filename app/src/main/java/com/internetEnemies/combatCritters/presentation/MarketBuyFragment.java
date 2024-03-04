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

public class MarketBuyFragment extends Fragment {
    private ItemGridFragment<Transaction> gridFrag;
    private FragmentMarketplaceBinding binding;
    private MarketplaceViewModel selectedOffersViewModel;


    public MarketBuyFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMarketplaceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        selectedOffersViewModel = viewModelProvider.get(MarketplaceViewModel.class);
        this.selectedOffersViewModel.getSelectedPosition().observe(this.getViewLifecycleOwner(), selectedPosition -> displayDeal());
    }

    private void displayDeal() {

    }
}
