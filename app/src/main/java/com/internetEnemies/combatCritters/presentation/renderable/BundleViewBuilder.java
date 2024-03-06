package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;

/**
 * BundleViewBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Builder for bundle views. Bundles are of the form List<ItemStack<?>>.
 */
public class BundleViewBuilder {
    private final View bundleView;

    public BundleViewBuilder(Context context, ViewGroup parent){
        this.bundleView = LayoutInflater.from(context).inflate(R.layout.bundle, parent, false);
    }

    /**
     * @return bundleView
     */
    public View getBundleView() {return bundleView;}

    /**
     * Sets the String to be displayed in bundleView.
     *
     * @param contents the String to be displayed.
     */
    public void setContents(String contents) {
        TextView view = bundleView.findViewById(R.id.bundleContents);
        view.setText(contents);
    }
}
