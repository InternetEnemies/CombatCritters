package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * CardRenderer.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    provide a View object for a card
 */
public class CardRenderer extends ItemRenderer<Card> {
    public CardRenderer(Card item, Context context) {
        super(item,context);
    }
    @Override
    public View getView(View view, ViewGroup parent) {

        CardViewBuilder builder = new CardViewBuilder(this.getContext(), parent);
        this.getItem().clone(builder);

        return builder.getCardView();
    }

    /**
     * helper function for getting cards from card renderers
     * @param cards list of cards
     * @param context context for the view
     * @return List of CardRenderers
     */
    public static List<ItemRenderer<Card>> getRenderers( List<Card> cards , Context context) {
        List<ItemRenderer<Card>> renderers = new ArrayList<>();
        for( Card card : cards ){
            renderers.add(new CardRenderer(card, context));
        }
        return renderers;
    }
}
