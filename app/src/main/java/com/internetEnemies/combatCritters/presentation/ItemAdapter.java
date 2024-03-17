package com.internetEnemies.combatCritters.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.List;
/**
 * ItemAdapter.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     14-March-2024
 *
 * @PURPOSE:     Adapter for recyclerview. Accepts any item that extends ItemRenderer.
 */
public class ItemAdapter<T> extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private final List<ItemRenderer<T>> items;
    private final boolean highlightItems;
    private int selectedItem = -1;
    private final IOnItemClickListener<T> listener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout itemFrameLayout;
        public FrameLayout overlay;

        public ViewHolder(View v) {
            super(v);
            itemFrameLayout = v.findViewById(R.id.item_grid_child);
            overlay = v.findViewById(R.id.item_grid_select_overlay);
        }
    }

    //Setting the listener to null will disable item clicking.
    public ItemAdapter(List<ItemRenderer<T>> items, IOnItemClickListener<T> listener, boolean highlightItems) {
        this.items = items;
        this.listener = listener;
        this.highlightItems = highlightItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemFrameLayout.removeAllViews();
        ItemRenderer<T> itemRenderer = items.get(position);
        View itemView = itemRenderer.getView(null, holder.itemFrameLayout);
        holder.itemFrameLayout.addView(itemView);

        holder.itemView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION && listener != null) {
                if (selectedItem == currentPosition) {
                    selectedItem = -1;
                } else {
                    selectedItem = currentPosition;
                }
                notifyDataSetChanged();
                T item = itemRenderer.getItem();
                listener.onItemClick(item);
            }
        });

        if(highlightItems) {
            if (selectedItem == position) {
                holder.overlay.setVisibility(View.VISIBLE);
            } else {
                holder.overlay.setVisibility(View.GONE);
            }
        }
        else {
            holder.overlay.setVisibility(View.GONE);
        }

    }

    public void updateItems(List<ItemRenderer<T>> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Returns the currently selected item, or null if no item is selected.
     * @return The selected item, or null if none is selected.
     */
    public T getSelectedItem() {
        if (selectedItem >= 0 && selectedItem < items.size()) {
            return items.get(selectedItem).getItem();
        }
        return null;
    }

    public int getSelectedItemPosition() {
        return selectedItem;
    }

    public interface IOnItemClickListener<T> {
        void onItemClick(T item);
    }

}
