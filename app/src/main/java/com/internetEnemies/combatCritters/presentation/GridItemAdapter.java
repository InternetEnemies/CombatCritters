package com.internetEnemies.combatCritters.presentation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.internetEnemies.combatCritters.presentation.renderable.IRenderable;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.List;

public class GridItemAdapter<T> extends BaseAdapter {

    private final List<ItemRenderer<T>> items;

    public GridItemAdapter(List<ItemRenderer<T>> items){
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return items.get(position).getView(convertView, parent);
    }
}
