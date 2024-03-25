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
    private final float scaleFactor;
    public CardRenderer(Card item, Context context) {
        this(item, context, 1);
    }

    public CardRenderer(Card item, Context context, float scaleFactor) {
        super(item,context);
        this.scaleFactor = scaleFactor;
    }
    @Override
    public View getView(View view, ViewGroup parent) {
        ItemViewVisitor itemViewVisitor = new ItemViewVisitor(getContext(), parent, scaleFactor);
        this.getItem().accept(itemViewVisitor);
        return itemViewVisitor.getView();
    }

    /**
     * helper function for getting cards from card renderers
     * @param cards list of cards
     * @param context context for the view
     * @return List of CardRenderers
     */
    public static List<ItemRenderer<Card>> getRenderers( List<Card> cards , Context context) {
        return getRenderers(cards, context, 1);
    }

    /**
     * helper function for getting cards from card renderers
     * @param cards list of cards
     * @param context context for the view
     * @param scaleFactor the factor by which to scale the view
     * @return List of CardRenderers
     */
    public static List<ItemRenderer<Card>> getRenderers( List<Card> cards , Context context, float scaleFactor) {
        List<ItemRenderer<Card>> renderers = new ArrayList<>();
        for( Card card : cards ){
            renderers.add(new CardRenderer(card, context, scaleFactor));
        }
        return renderers;
    }
}
