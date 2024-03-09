package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;

/**
 * PackViewBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     09/March/2024
 *
 * @PURPOSE:    Builds the view for a Pack object.
 */
public class PackViewBuilder {
    private final View packView;

    public PackViewBuilder(View packView, String name) {
        this.packView = packView;
        TextView packText = packView.findViewById(R.id.packName);
        packText.setText(name);
    }

    public View getPackView() {
        return packView;
    }
}
