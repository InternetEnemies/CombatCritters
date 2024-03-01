package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

public class CardStackRenderer extends ItemRenderer<ItemStack<Card>>{
    //TODO implement this class properly
    public CardStackRenderer(ItemStack<Card> item, Context context) {
        super(item, context);
    }

    @Override
    public View getView(View view, ViewGroup parent) {
        return null;
    }

    public static List<ItemRenderer<Card>> getRenderers(List<ItemStack<Card>> cards, Context context) {

        return null;
    }

}
