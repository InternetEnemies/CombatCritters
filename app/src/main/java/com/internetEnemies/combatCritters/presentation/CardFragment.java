package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.databinding.FragmentCardBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.io.Serializable;

/**
 * CardFragment.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     Fragment for displaying a card.
 */
public class CardFragment extends Fragment {
    private static final String ARG_KEY = "card";

    public static CardFragment newInstance(Serializable card) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, card);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.internetEnemies.combatCritters.databinding.FragmentCardBinding binding = FragmentCardBinding.inflate(inflater, container, false);

        if(getArguments() != null) {
            Serializable cardSerializable = getArguments().getSerializable(ARG_KEY);
            if(cardSerializable instanceof Card) {
                Card card = (Card)cardSerializable;
                CardRenderer cardRenderer = new CardRenderer(card, getContext());

                View cardView = cardRenderer.getView(null, binding.cardContainer);

                float scaleFactor = 1.4f;
                cardView.setScaleX(scaleFactor);
                cardView.setScaleY(scaleFactor);

                binding.cardContainer.addView(cardView);
            }
        }



        return binding.getRoot();
    }
}
