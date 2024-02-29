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
import com.internetEnemies.combatCritters.presentation.renderable.IRenderable;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.ArrayList;
import java.util.List;

public class ItemGridFragment<T> extends Fragment {
    private final List<ItemRenderer<T>> items;
    private final GridItemAdapter<T> adapter;

    private GridView gridView;


    private final isItemSelected isItemSelected;
    private final onItemSelect onItemSelect;

    public ItemGridFragment(List<ItemRenderer<T>> items) {
        this(items, //set default callbacks
                idx -> {/* do nothing */},
                idx -> false
        );
    }

    public ItemGridFragment(List<ItemRenderer<T>> items, onItemSelect onItemSelect, isItemSelected isItemSelected){
        this.items = items;
        this.adapter = new GridItemAdapter<>(this.items, isItemSelected);

        this.onItemSelect = onItemSelect;
        this.isItemSelected = isItemSelected;
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