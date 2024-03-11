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
public class ItemAdapter<T> extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private final List<ItemRenderer<T>> items;
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

    public ItemAdapter(List<ItemRenderer<T>> items, IOnItemClickListener<T> listener) {
        this.items = items;
        this.listener = listener;
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

        if(selectedItem == position) {
            holder.overlay.setVisibility(View.VISIBLE);
        } else {
            holder.overlay.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface IOnItemClickListener<T> {
        void onItemClick(T item);
    }

}
