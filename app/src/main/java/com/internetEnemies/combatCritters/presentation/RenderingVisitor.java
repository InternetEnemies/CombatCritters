package com.internetEnemies.combatCritters.presentation;

import android.content.Context;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;

import java.util.ArrayList;
import java.util.List;

public class RenderingVisitor implements IItemVisitor {
    private Context context;
    private List<ItemRenderer<? extends IItem>> renderers;

    public RenderingVisitor(Context context) {
        this.context = context;
        this.renderers = new ArrayList<>();
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        renderers.add(new CardRenderer(card, context));
    }

    @Override
    public void visitItemCard(ItemCard card) {
        renderers.add(new CardRenderer(card, context));
    }

    @Override
    public void visitPack(Pack pack) {
        for (Card card : pack.getSetList()) {
            renderers.add(new CardRenderer(card, context));
        }
    }

    @Override
    public void visitCurrency(Currency currency) {}

    public List<ItemRenderer<? extends IItem>> getRenderers() {
        return renderers;
    }
}
