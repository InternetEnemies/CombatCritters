package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.List;

public class GridItemAdapter<T> extends BaseAdapter {
    private final List<ItemRenderer<T>> items;
    private final isItemSelected isSelected;

    public GridItemAdapter(List<ItemRenderer<T>> items, isItemSelected isSelected){
        this.items = items;
        this.isSelected = isSelected;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position).getItem();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get grid view container item
        ConstraintLayout container = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_item, parent, false); //todo resolve ViewHolder issue
        FrameLayout inner = container.findViewById(R.id.item_grid_child);
        // inject card into container
        inner.addView(items.get(position).getView(convertView,inner));

        // remove selection overlay if not selected
        FrameLayout selectOverlay = container.findViewById(R.id.item_grid_select_overlay);
        if(!isSelected.isSelected(position)) {
            selectOverlay.setVisibility(View.INVISIBLE);
        }

        return container;
    }
}