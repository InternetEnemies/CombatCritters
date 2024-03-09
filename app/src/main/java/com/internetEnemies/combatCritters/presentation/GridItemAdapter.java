package com.internetEnemies.combatCritters.presentation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.List;

/**
 * GridItemAdapter.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    Adapts ItemRenders for a GridView
 */
public class GridItemAdapter<T> extends BaseAdapter {
    private final List<ItemRenderer<T>> items;
    private final isItemSelected isSelected;

    public GridItemAdapter(List<ItemRenderer<T>> items) {
        this(items, idx -> false);//default behavior is that nothing is ever selected
    }
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
        ConstraintLayout container = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_item, parent, false);
        FrameLayout inner = container.findViewById(R.id.item_grid_child);
        // inject card into container
        inner.addView(items.get(position).getView(convertView,inner));
        Log.d("here1", items.get(position).getClass().toString());

        // remove selection overlay if not selected
        FrameLayout selectOverlay = container.findViewById(R.id.item_grid_select_overlay);
        if(!isSelected.isSelected(position)) {
            selectOverlay.setVisibility(View.INVISIBLE);
        }

        return container;
    }
}