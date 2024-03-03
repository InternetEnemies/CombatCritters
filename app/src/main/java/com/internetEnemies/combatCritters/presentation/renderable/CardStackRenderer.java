package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * CardStackRenderer.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    render an ItemStack containing cards
 */
public class CardStackRenderer extends ItemRenderer<ItemStack<Card>>{
    public CardStackRenderer(ItemStack<Card> item, Context context) {
        super(item, context);
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        ConstraintLayout container = (ConstraintLayout) LayoutInflater.from(this.getContext()).inflate(R.layout.item_stack_container,parent,false);
        FrameLayout inner = container.findViewById(R.id.item_container);

        View cardView = new CardRenderer(this.getItem().getItem(),this.getContext()).getView(null,inner);
        inner.addView(cardView);

        TextView amount = container.findViewById(R.id.item_count);
        amount.setText(String.valueOf(this.getItem().getAmount()));

        return container;
    }

    /**
     * helper function for converting stacks of cards into CardStackRenderers
     * @param items stacks of cards
     * @param context context for the render
     * @return list of ItemRenderers
     */
    public static List<ItemRenderer<ItemStack<Card>>> getRenderers(List<ItemStack<Card>> items, Context context) {
        List<ItemRenderer<ItemStack<Card>>> renderers = new ArrayList<>();
        for( ItemStack<Card> itemStack : items ){
            renderers.add(new CardStackRenderer(itemStack, context));
        }

        return renderers;
    }

}
