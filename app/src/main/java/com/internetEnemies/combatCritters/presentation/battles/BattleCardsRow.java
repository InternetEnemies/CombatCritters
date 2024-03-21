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
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.CardState;
import com.internetEnemies.combatCritters.presentation.CardFragment;

import java.util.ArrayList;
import java.util.List;

public class BattleCardsRow extends Fragment {

    private BattleCardsRowViewModel mViewModel;
    private BattleCardViewModel[] cards;

    public static BattleCardsRow newInstance() {
        return new BattleCardsRow();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_battle_cards_row, container, false);
        BattleCardFragment card1 = (BattleCardFragment) getChildFragmentManager().findFragmentById(R.id.card1);
        BattleCardFragment card2 = (BattleCardFragment) getChildFragmentManager().findFragmentById(R.id.card2);
        BattleCardFragment card3 = (BattleCardFragment) getChildFragmentManager().findFragmentById(R.id.card3);
        BattleCardFragment card4 = (BattleCardFragment) getChildFragmentManager().findFragmentById(R.id.card4);
        BattleCardFragment card5 = (BattleCardFragment) getChildFragmentManager().findFragmentById(R.id.card5);
        cards = new BattleCardViewModel[]{
                new ViewModelProvider(card1).get(BattleCardViewModel.class),
                new ViewModelProvider(card2).get(BattleCardViewModel.class),
                new ViewModelProvider(card3).get(BattleCardViewModel.class),
                new ViewModelProvider(card4).get(BattleCardViewModel.class),
                new ViewModelProvider(card5).get(BattleCardViewModel.class)
        };

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BattleCardsRowViewModel.class);
        mViewModel.getCardList().observe(this,this::handleRowChange);
    }

    public void handleRowChange(List<CardState> cardStates) {
        if (cards.length > cardStates.size()) return; //todo remove when null objects are added
        for (int i = 0; i < cards.length; i++) {
            cards[i].setCard(cardStates.get(i));
        }
    }

}