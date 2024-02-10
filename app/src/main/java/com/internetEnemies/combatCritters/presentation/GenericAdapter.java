package com.internetEnemies.combatCritters.presentation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class GenericAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> items;
    private int selectedPosition = -1;

    public GenericAdapter(Context context, List<T> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        }

        T item = getItem(position);
        bindView(view, item, position);

        highlightView(view, selectedPosition == position, position);

        return view;
    }

    protected abstract int getLayoutId();
    protected abstract void bindView(View view, T item, int position);

    protected abstract void highlightView(View view, boolean isSelected, int position);

    public void clearSelection() {
        selectedPosition = -1;
        notifyDataSetChanged();
    }
    public void updateItems(List<T> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
