package com.internetEnemies.combatCritters.presentation;

import com.internetEnemies.combatCritters.Logic.IItemVisitor;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

public class ItemNameFetcher implements IItemVisitor {
    private String itemName;

    public ItemNameFetcher() {
        itemName = null;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        itemName = card.getName();
    }

    @Override
    public void visitPack(Pack pack) {
        itemName = pack.getName();
    }

    @Override
    public void visitCurrency(Currency currency) {/* Do nothing */}
    @Override
    public void visitItemCard(ItemCard card) {/* Do nothing */}
}
