package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ItemRenderer<Pack>> getRenderers(List<Pack> packs , Context context) {
        List<ItemRenderer<Pack>> renderers = new ArrayList<>();
        for( Pack pack : packs ){
            renderers.add(new PackRenderer(pack, context));
        }
        return renderers;
    }
}
