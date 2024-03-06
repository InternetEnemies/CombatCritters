package com.internetEnemies.combatCritters.presentation.renderable;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

/**
 * BundleNameBuilderVisitor.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Build a String containing the names of the packs and cards in a bundle.
 *               Bundles are of the form List<ItemStack<?>>
 */
public class BundleNameBuilderVisitor implements IItemVisitor {
    private final StringBuilder builder;

    public BundleNameBuilderVisitor() {
        builder= new StringBuilder();
    }

    /*
     * @return the name of the bundle.
     */
    public String getBundleName() {
        return builder.toString();
    }

    /**
     * Add card.getName() to the bundle name.
     *
     * @param card CritterCard that is to be added to the bundle name.
     */
    @Override
    public void visitCritterCard(CritterCard card) {
        builder.append("- ");
        builder.append(card.getName());
        builder.append("\n");
    }

    /**
     * Add pack.getName() to the bundle name.
     *
     * @param pack Pack that is to be added to the bundle name.
     */
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
