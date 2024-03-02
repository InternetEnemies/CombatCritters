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
    } //todo this rendering code is kinda smelly (including the card renderer, should be solved with visitors, leaving this todo for now

    public static List<ItemRenderer<ItemStack<Card>>> getRenderers(List<ItemStack<Card>> items, Context context) {
        List<ItemRenderer<ItemStack<Card>>> renderers = new ArrayList<>();
        for( ItemStack<Card> itemStack : items ){
            renderers.add(new CardStackRenderer(itemStack, context));
        }

        return renderers;
    }

}
