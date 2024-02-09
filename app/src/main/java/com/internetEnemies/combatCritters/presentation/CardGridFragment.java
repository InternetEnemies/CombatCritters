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
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;
import java.util.Map;

public class CardGridFragment extends Fragment {

    private GridView gridView;
    private CardAdapter cardAdapter;
    private Card selectedCard;

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
        cardSelectSetup();
    }

    public void setCardAdapter(CardAdapter adapter) {
        this.cardAdapter = adapter;
        if (gridView != null) {
            gridView.setAdapter(adapter);
        }
    }

    public void updateGridView(List<Card> cardList){

    }

    public void updateGridView(Map<Card, Integer> cardMap) {}

    private void cardSelectSetup() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // If the currently selected card is the same card as the previously selected card, remove the highlight
            if (cardAdapter.getItem(position).equals(selectedCard)) {
                selectedCard = null;
                cardAdapter.deselectCard();
            }
            // Highlight card if no card was previously selected OR if the previously selected card is different than the current selection
            else {
                selectedCard = cardAdapter.getItem(position);
                cardAdapter.setSelectedCard(position);
            }
            cardAdapter.notifyDataSetChanged();
        });

    }
}
