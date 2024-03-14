package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.internetEnemies.combatCritters.Logic.ItemStackListExtractor;
import com.internetEnemies.combatCritters.databinding.ActivityMarketplace2Binding;
import com.internetEnemies.combatCritters.databinding.FragmentBundle2Binding;
import com.internetEnemies.combatCritters.databinding.FragmentBundleBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bundle2Fragment extends Fragment {

    private static final String ARG_KEY = "transaction";
    private FragmentBundle2Binding binding;
    private ItemAdapter<Card> cardsAdapter;
    private ItemAdapter<Pack> packsAdapter;

    public static Bundle2Fragment newInstance(Serializable transaction) {
        Bundle2Fragment fragment = new Bundle2Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, transaction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBundle2Binding.inflate(inflater, container, false);

        if(getArguments() != null) {
            Serializable transactionSerializable = getArguments().getSerializable(ARG_KEY);
            if(transactionSerializable instanceof MarketTransaction) {
                MarketTransaction transaction = (MarketTransaction)transactionSerializable;
                ItemStackListExtractor extractor = new ItemStackListExtractor(transaction.getReceived());
                List<Card> cards = extractor.getCards();
                List<Pack> packs = extractor.getPacks();

                cardsAdapter = new ItemAdapter<>(CardRenderer.getRenderers(cards, getContext()), null, false);
                packsAdapter = new ItemAdapter<>(PackRenderer.getRenderers(packs, getContext()), null, false);
            }
        }

        setupRecyclerView();
        setupTabLayout();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        int numberOfColumns = 3;
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        SpacingItemDecoration itemDecoration = new SpacingItemDecoration(15);
        binding.recyclerView.addItemDecoration(itemDecoration);
    }

    private void setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: // Cards tab selected
                        binding.recyclerView.setAdapter(cardsAdapter);
                        break;
                    case 1: // Packs tab selected
                        binding.recyclerView.setAdapter(packsAdapter);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

}
