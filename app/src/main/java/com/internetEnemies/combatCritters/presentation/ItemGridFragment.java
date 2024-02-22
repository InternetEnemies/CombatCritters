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
    private int selected;

    private final ItemGridViewModel<T> stateHandler;

    public ItemGridFragment(List<ItemRenderer<T>> items, ItemGridViewModel<T> stateHandler){
        this.items = items;
        this.selected = -1;
        this.adapter = new GridItemAdapter<>(this.items, item -> item == this.selected);
        this.stateHandler = stateHandler;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO rename the elements in these
        View view = inflater.inflate(R.layout.fragment_card_grid, container, false);
        gridView = view.findViewById(R.id.cardGridView);

        //TODO remove this bandaid (that or fix it when changing the state managment)
        stateHandler.getSelectedItem().observe(getViewLifecycleOwner(),item -> {
            if (item == null) {
                selected = -1;
            }
            this.adapter.notifyDataSetChanged();
        });

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
        this.adapter.notifyDataSetChanged();
        /* TODO
         *  this method appears slow, worth looking into something better
         *  (quick search indicates this is O(n^2) which might be part of the issue)
         *  Solution might involve switching the type of list, or adding an object to wrap the list
         *  best might be to extend the arraylist class, although that'd be hard to do without SOLID violations
         */
    }

    /**
     * setup the onclick listener for the GridView
     */
    private void itemSelectSetup() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            if( this.selected == position) {
                clearSelection(); //Deselect if the selected item is selected again
            } else {
                setSelected(position);
            }
        });
    }

    public void clearSelection(){
        setSelected(-1);
    }

    /**
     * set the selected position within the grid
     * @param position position to set to
     */
    private void setSelected(int position) {
        this.selected = position;
        this.adapter.notifyDataSetChanged();
        T selected = position >= 0 ? this.items.get(position).getItem() : null; // return null if index OOB
        this.stateHandler.setSelectedItem(selected);
    }
}