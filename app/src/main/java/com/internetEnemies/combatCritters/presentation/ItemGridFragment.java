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
    public ItemGridFragment(List<ItemRenderer<T>> items){
        this.items = items;
        this.adapter = new GridItemAdapter<>(this.items);
    }

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO rename the elements in these
        View view = inflater.inflate(R.layout.fragment_card_grid, container, false);
        gridView = view.findViewById(R.id.cardGridView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView.setAdapter(adapter);
    }

    //TODO selections
}
