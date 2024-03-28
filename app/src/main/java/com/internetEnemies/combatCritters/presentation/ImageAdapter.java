package com.internetEnemies.combatCritters.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ImageAdapter.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     27/March/24
 *
 * @PURPOSE:    Adapter for images.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private final List<Integer> imageResIds;

    public ImageAdapter() {
        imageResIds = new ArrayList<>();
    }
    public ImageAdapter(List<Integer> imageResIds) {
        this.imageResIds = imageResIds;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_container, parent, false);
        return new ImageViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.imageView.setImageResource(imageResIds.get(position));
    }

    @Override
    public int getItemCount() {
        return imageResIds.size();
    }

    /**
     * Add image to the adapter.
     *
     * @param imageResId Res location to fetch image from
     */
    public void addImage(int imageResId) {
        imageResIds.add(imageResId);
        notifyItemInserted(imageResIds.size() - 1);
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}
