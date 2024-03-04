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
//        this.getItem().clone(builder);
        builder.setName(this.getItem().getName());
        return builder.getPackView();
    }

    /**
     * helper function for getting cards from card renderers
     * @param cards list of cards
     * @param context context for the view
     * @return List of CardRenderers
     */
    public static List<ItemRenderer<Card>> getRenderers(List<Card> cards , Context context) {
        List<ItemRenderer<Card>> renderers = new ArrayList<>();
        for( Card card : cards ){
            renderers.add(new CardRenderer(card, context));
        }
        return renderers;
    }
}
