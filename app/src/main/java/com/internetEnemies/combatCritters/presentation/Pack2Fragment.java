package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.internetEnemies.combatCritters.Logic.ItemStackListExtractor;
import com.internetEnemies.combatCritters.databinding.FragmentBundle2Binding;
import com.internetEnemies.combatCritters.databinding.FragmentPack2Binding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.io.Serializable;
import java.util.List;

public class Pack2Fragment extends Fragment {
    private static final String ARG_KEY = "transaction";
    private FragmentPack2Binding binding;

    public static Pack2Fragment newInstance(Serializable transaction) {
        Pack2Fragment fragment = new Pack2Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, transaction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPack2Binding.inflate(inflater, container, false);

        if(getArguments() != null) {
            Serializable transactionSerializable = getArguments().getSerializable(ARG_KEY);
            if(transactionSerializable instanceof MarketTransaction) {
                MarketTransaction transaction = (MarketTransaction)transactionSerializable;

                Pack pack = (Pack)transaction.getReceivedFirstItem().getItem();
                List<ItemRenderer<Card>> cardRenderers = CardRenderer.getRenderers(pack.getSetList(), getContext());

                setupRecyclerView(cardRenderers);
            }
        }

        return binding.getRoot();
    }

    private void setupRecyclerView(List<ItemRenderer<Card>> cardRenderers) {
        int numberOfColumns = 3;
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        SpacingItemDecoration itemDecoration = new SpacingItemDecoration(15);
        binding.recyclerView.addItemDecoration(itemDecoration);

        ItemAdapter<Card> cardAdapter = new ItemAdapter<>(cardRenderers, null, false);
        binding.recyclerView.setAdapter(cardAdapter);
    }
}
