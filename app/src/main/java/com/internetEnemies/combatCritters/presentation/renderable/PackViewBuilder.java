package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.internetEnemies.combatCritters.R;

/**
 * PackViewBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Builder for Pack views.
 */
public class PackViewBuilder  {
    private final View packView;

    public PackViewBuilder(Context context, ViewGroup parent){
        this.packView = LayoutInflater.from(context).inflate(R.layout.pack, parent, false);
    }

    public View getPackView() {return packView;}

    /**
     * Applies the pack name to the view.
     *
     * @param name name of the pack.
     */
    public void setName(String name) {
        TextView view = packView.findViewById(R.id.packName);
        view.setText(name);
    }
}
