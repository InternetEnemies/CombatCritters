package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.battles.CardState;
import com.internetEnemies.combatCritters.presentation.renderable.ItemViewVisitor;

public class BattleCardFragment extends Fragment {

    public static BattleCardFragment newInstance() {
        return new BattleCardFragment();
    }

    private BattleCardViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_battle_card, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BattleCardViewModel.class);
        mViewModel.getCard().observe(this, this::handleCardChange);
    }

    public void handleCardChange(CardState card) {
        if (card == null) return; // todo remove, should be a nullObject instead of null
        FrameLayout layout = this.getView().findViewById(R.id.cardContainer);
        layout.removeAllViews();
        ItemViewVisitor visitor = new ItemViewVisitor(this.getContext(), layout);
        card.getCard().accept(visitor);
        View view = visitor.getView();
        layout.addView(view);
    }



}