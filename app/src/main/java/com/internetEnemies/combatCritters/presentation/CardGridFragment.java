package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.R;

public class CardGridFragment extends Fragment {

    private GridView gridView;
    private CardAdapter cardAdapter;

    public static CardGridFragment newInstance() {
        return new CardGridFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_grid, container, false);
        gridView = view.findViewById(R.id.cardGridView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (cardAdapter != null) {
            gridView.setAdapter(cardAdapter);
        }
    }

    public void setCardAdapter(CardAdapter adapter) {
        this.cardAdapter = adapter;
        if (gridView != null) {
            gridView.setAdapter(adapter);
        }
    }

}
