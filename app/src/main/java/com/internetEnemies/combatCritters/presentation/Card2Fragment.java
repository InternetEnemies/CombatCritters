package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.Logic.ItemStackListExtractor;
import com.internetEnemies.combatCritters.databinding.FragmentBundle2Binding;
import com.internetEnemies.combatCritters.databinding.FragmentCard2Binding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.io.Serializable;
import java.util.List;

public class Card2Fragment extends Fragment {
    private static final String ARG_KEY = "transaction";
    private final float SCALE_FACTOR = 1.4f;

    public static Card2Fragment newInstance(Serializable transaction) {
        Card2Fragment fragment = new Card2Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, transaction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.internetEnemies.combatCritters.databinding.FragmentCard2Binding binding = FragmentCard2Binding.inflate(inflater, container, false);

        if(getArguments() != null) {
            Serializable transactionSerializable = getArguments().getSerializable(ARG_KEY);
            if(transactionSerializable instanceof MarketTransaction) {
                MarketTransaction transaction = (MarketTransaction)transactionSerializable;
                Card card = (Card)transaction.getReceivedFirstItem().getItem();
                CardRenderer cardRenderer = new CardRenderer(card, getContext());

                View cardView = cardRenderer.getView(null, binding.cardContainer);

                cardView.setScaleX(SCALE_FACTOR);
                cardView.setScaleY(SCALE_FACTOR);

                binding.cardContainer.addView(cardView);
            }
        }



        return binding.getRoot();
    }
}
