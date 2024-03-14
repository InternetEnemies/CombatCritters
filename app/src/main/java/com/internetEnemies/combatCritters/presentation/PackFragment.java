package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.internetEnemies.combatCritters.databinding.FragmentPackBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.io.Serializable;
import java.util.List;

/**
 * PackFragment.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     Displays the cards in a pack using a recyclerview.
 */
public class PackFragment extends Fragment {
    private static final String ARG_KEY = "pack";
    private FragmentPackBinding binding;

    public static PackFragment newInstance(Serializable pack) {
        PackFragment fragment = new PackFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, pack);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPackBinding.inflate(inflater, container, false);

        if(getArguments() != null) {
            Serializable packSerializable = getArguments().getSerializable(ARG_KEY);
            if(packSerializable instanceof Pack) {
                Pack pack = (Pack)packSerializable;

                List<ItemRenderer<Card>> cardRenderers = CardRenderer.getRenderers(pack.getSetList(), getContext());

                setupRecyclerView(cardRenderers);
            }
        }

        return binding.getRoot();
    }

    private void setupRecyclerView(List<ItemRenderer<Card>> cardRenderers) {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.addItemDecoration(new SpacingItemDecoration());
        ItemAdapter<Card> cardAdapter = new ItemAdapter<>(cardRenderers, null, false);
        binding.recyclerView.setAdapter(cardAdapter);
    }
}
