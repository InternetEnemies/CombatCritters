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
import android.widget.TextView;

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
        View view = inflater.inflate(R.layout.fragment_battle_card, container, false);
        view.setOnClickListener((View button) -> mViewModel.onClick());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BattleCardViewModel.class);
        mViewModel.getCard().observe(this, this::handleCardChange);
    }

    public void handleCardChange(CardState card) {
        FrameLayout layout = this.getView().findViewById(R.id.cardContainer);
        layout.removeAllViews();

        if (card != null) { // only render a new card if there is a new card to render
            ItemViewVisitor visitor = new ItemViewVisitor(this.getContext(), layout);
            card.getCard().accept(visitor);
            View view = visitor.getView();

            // set health to state health
            TextView health = view.findViewById(R.id.hpText);
            health.setText(String.valueOf(card.getCurrHealth()));

            layout.addView(view);
        }
    }



}