package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

public class RendererVisitor implements IItemVisitor {
    private final Context context;
    private final FrameLayout inner;
    private View view;

    public RendererVisitor(Context context, FrameLayout inner) {
        this.context = context;
        this.inner = inner;
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        view = new CardRenderer(card, context).getView(null, inner);
        inner.addView(view);
    }

    @Override
    public void visitPack(Pack pack) {
        view = new PackRenderer(pack, context).getView(null, inner);
        inner.addView(view);
    }

    @Override
    public void visitCurrency(Currency currency) {/* Do nothing */}
    @Override
    public void visitItemCard(ItemCard card) {/* Do nothing */}
}
