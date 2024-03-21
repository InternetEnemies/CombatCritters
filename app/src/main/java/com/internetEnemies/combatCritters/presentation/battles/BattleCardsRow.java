package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.CardFragment;

public class BattleCardsRow extends Fragment {

    private BattleCardsRowViewModel mViewModel;

    public static BattleCardsRow newInstance() {
        return new BattleCardsRow();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_battle_cards_row, container, false);
        CardFragment card1 = (CardFragment) getChildFragmentManager().findFragmentById(R.id.card1);
        //todo make another fragment for GameCards (also rename GameCard CardState)
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BattleCardsRowViewModel.class);
        // TODO: Use the ViewModel
    }

}