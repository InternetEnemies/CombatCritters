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
    public interface OnCardSelectedListener {
        void onCardSelected(Card card);
    }

    private OnCardSelectedListener cardSelectedListener;
    private GridView gridView;
    private CardAdapter adapter;
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
        if (adapter != null) {
            gridView.setAdapter(adapter);
        }
        itemSelectSetup();
    }

    public void setOnCardSelectedListener(OnCardSelectedListener listener) {
        this.cardSelectedListener = listener;
    }

    private void notifyCardSelected(Card card) {
        if (cardSelectedListener != null) {
            cardSelectedListener.onCardSelected(card);
        }
    }

    public void setAdapter(CardAdapter adapter) {
        this.adapter = adapter;
        if (gridView != null) {
            gridView.setAdapter(adapter);
        }
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    //TODO: The implementation of these two methods could be done better
    public void updateGridView(List<Card> itemList){
        adapter.updateCards(itemList);
    }

    public void updateGridView(Map<Card, Integer> cardMap) {
        if(adapter instanceof CardWithQuantityAdapter) {
            ((CardWithQuantityAdapter)adapter).updateCards(cardMap);
        }
    }

    private void itemSelectSetup() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Card currentSelection = adapter.getItem(position);
            // If the currently selected item is the same as the previously selected item, remove the highlight
            if (currentSelection.equals(selectedCard)) {
                selectedCard = null;
                adapter.clearSelection();
            }
            // Highlight item if no item was previously selected OR if the previously selected item is different than the current selection
            else {
                selectedCard = currentSelection;
                adapter.setSelectedPosition(position);
            }
            notifyCardSelected(selectedCard);
            adapter.notifyDataSetChanged();
        });
    }

    public void clearSelection() {
        selectedCard = null;
        adapter.clearSelection();
    }
}
