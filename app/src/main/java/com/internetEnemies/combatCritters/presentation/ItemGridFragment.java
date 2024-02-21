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
    private final List<ISelectListener<T>> selectListeners;

    private GridView gridView;
    private int selected;

    public ItemGridFragment(List<ItemRenderer<T>> items){
        this.items = items;
        this.selected = -1;
        this.adapter = new GridItemAdapter<>(this.items, item -> item == this.selected);
        this.selectListeners = new ArrayList<>();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO rename the elements in these
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
        this.adapter.notifyDataSetChanged();
        /* TODO
         *  this method appears slow, worth looking into something better
         *  (quick search indicates this is O(n^2) which might be part of the issue)
         *  Solution might involve switching the type of list, or adding an object to wrap the list
         *  best might be to extend the arraylist class, although that'd be hard to do without SOLID violations
         */
        clearSelection();
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
        fireSelectEvent();
    }

    /**
     * @return get the currently selected item from the grid
     */
    public T getSelected() {
        return this.selected >= 0 ? adapter.getItem(this.selected) : null;
    }

    /**
     * add a listener that fires whenever the selected item is changed
     * @param onSelect function that will be called on change
     */
    public void addSelectListener(ISelectListener<T> onSelect){
        selectListeners.add(onSelect);
    }

    private void fireSelectEvent(){ //TODO extract this functionality to a class similar to a svelte store (probably the view model) that is passed as an argument
        T selectedItem = getSelected();
        for(ISelectListener<T> onSelect: selectListeners) {
            onSelect.onSelect(selectedItem);
        }
    }
}