package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.R;

public class BattleCardsRow extends Fragment {

    private BattleCardsRowViewModel mViewModel;

    public static BattleCardsRow newInstance() {
        return new BattleCardsRow();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_battle_cards_row, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BattleCardsRowViewModel.class);
        // TODO: Use the ViewModel
    }

}