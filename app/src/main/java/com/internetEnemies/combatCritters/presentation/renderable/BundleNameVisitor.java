package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

public class BundleNameVisitor implements IItemVisitor {
    private final StringBuilder builder;

    public BundleNameVisitor() {
        builder= new StringBuilder();
    }

    public String getBundleName() {
        return builder.toString();
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        builder.append("- ");
        builder.append(card.getName());
        builder.append("\n");
    }

    @Override
    public void visitPack(Pack pack) {
        builder.append("- ");
        builder.append(pack.getName());
        builder.append("\n");
    }

    @Override
    public void visitCurrency(Currency currency) {/* Do nothing */}
    @Override
    public void visitItemCard(ItemCard card) {/* Do nothing */}
}
