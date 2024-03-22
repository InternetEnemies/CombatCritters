package com.internetEnemies.combatCritters.presentation;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * SpacingItemDecoration.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     14-March-2024
 *
 * @PURPOSE:     Spaces the items of a Recyclerview.
 */
public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    private final int horizontalSpacing;
    private final int verticalSpacing;

    public SpacingItemDecoration(int horizontalSpacing, int verticalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
        this.verticalSpacing = verticalSpacing;
    }
    public  SpacingItemDecoration() {
        //Default to 15 spacing
        this.horizontalSpacing = 15;
        this.verticalSpacing = 15;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = horizontalSpacing;
        outRect.right = horizontalSpacing;
        outRect.top = verticalSpacing;
        outRect.bottom = verticalSpacing;
    }
}
