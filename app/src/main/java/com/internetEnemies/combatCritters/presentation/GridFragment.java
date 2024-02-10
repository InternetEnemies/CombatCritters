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

public class GridFragment<A extends GenericAdapter<T>, T> extends Fragment {

    private GridView gridView;
    private A genericAdapter;
    private T selectedItem;

    public static <A extends GenericAdapter<T>, T> GridFragment<A, T> newInstance() {
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
        itemSelectSetup();
    }

    public void setAdapter(A adapter) {
        this.genericAdapter = adapter;
        if (gridView != null) {
            gridView.setAdapter(adapter);
        }
    }

    public void updateGridView(List<T> itemList){
        genericAdapter.updateItems(itemList);
    }

    private void itemSelectSetup() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // If the currently selected item is the same as the previously selected item remove the highlight
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
