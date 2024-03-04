package com.internetEnemies.combatCritters.presentation.renderable;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

public class BundleNameBuilder implements IItemVisitor {
    private final StringBuilder builder;

    public BundleNameBuilder() {
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
