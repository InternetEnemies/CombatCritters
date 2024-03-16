package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemGridFragment.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    GridView Fragment for ItemRenderers.
 */
public class ItemGridFragment<T> extends Fragment {
    private final List<ItemRenderer<T>> items;
    private final GridItemAdapter<T> adapter;
    private GridView gridView;
    private final onItemSelect onItemSelect;
    private int numColumns;

    public ItemGridFragment(List<ItemRenderer<T>> items, int numColumns) {
        this(items, //set default callbacks
                idx -> {/* do nothing */},
                idx -> false,
                numColumns
        );
        this.numColumns = numColumns;
    }

    public ItemGridFragment(List<ItemRenderer<T>> items, onItemSelect onItemSelect, isItemSelected isItemSelected, int numColumns){
        this.items = items;
        this.numColumns = numColumns;
        this.adapter = new GridItemAdapter<>(this.items, isItemSelected);

        this.onItemSelect = onItemSelect;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_grid, container, false);
        gridView = view.findViewById(R.id.cardGridView);

        itemSelectSetup();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setNumColumns(numColumns);
        gridView.setAdapter(adapter);
    }

    /**
     * update the contents of the grid
     * @param items new list of items for the list
     */
    public void updateItems(List<ItemRenderer<T>> items) {
        //Clear then refill the list
        this.items.removeAll(new ArrayList<>(this.items));
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * setup the onclick listener for the GridView
     */
    private void itemSelectSetup() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            onItemSelect.onSelected(position);
        });
    }

    /**
     * tell the grid the data has changed (List or selection)
     */
    public void notifyDataSetChanged(){
        this.adapter.notifyDataSetChanged();
    }
}

/**
 * callback interface for when an item is clicked
 */
interface onItemSelect {
    void onSelected(int idx);
}

/**
 * callback interface so the item grid can check if something is selected
 */
interface isItemSelected {
    boolean isSelected(int idx);
}