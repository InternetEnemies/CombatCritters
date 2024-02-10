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

import java.util.List;
import java.util.Map;

public class GridFragment<T> extends Fragment {

    private GridView gridView;
    private GenericAdapter<T> genericAdapter;
    private T selectedItem;

    public static <T> GridFragment<T> newInstance() {
        return new GridFragment<>();
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
        if (genericAdapter != null) {
            gridView.setAdapter(genericAdapter);
        }
        cardSelectSetup();
    }

    public void setCardAdapter(GenericAdapter<T> adapter) {
        this.genericAdapter = adapter;
        if (gridView != null) {
            gridView.setAdapter(adapter);
        }
    }

    public void updateGridView(List<T> cardList){
        genericAdapter.updateItems(cardList);
    }

    private void cardSelectSetup() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // If the currently selected item is the item card as the previously selected item, remove the highlight
            if (genericAdapter.getItem(position).equals(selectedItem)) {
                selectedItem = null;
                genericAdapter.clearSelection();
            }
            // Highlight item if no item was previously selected OR if the previously selected item is different than the current selection
            else {
                selectedItem = genericAdapter.getItem(position);
                genericAdapter.setSelectedPosition(position);
            }
            genericAdapter.notifyDataSetChanged();
        });

    }
}
