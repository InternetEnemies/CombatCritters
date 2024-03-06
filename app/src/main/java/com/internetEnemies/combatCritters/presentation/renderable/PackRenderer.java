package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

/**
 * PackRenderer.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Provides the view for a Pack.
 */
public class PackRenderer extends ItemRenderer<Pack>{
    public PackRenderer(Pack item, Context context) {
        super(item,context);
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        PackViewBuilder builder = new PackViewBuilder(this.getContext(), parent);
        builder.setName(this.getItem().getName());
        return builder.getPackView();
    }

    /**
     * helper function for getting packs from pack renderers
     * @param packs list of cards
     * @param context context for the view
     * @return List of PackRenderers
     */
    public static List<ItemRenderer<Pack>> getRenderers(List<Pack> packs , Context context) {
        List<ItemRenderer<Pack>> renderers = new ArrayList<>();
        for( Pack pack : packs ){
            renderers.add(new PackRenderer(pack, context));
        }
        return renderers;
    }
}
