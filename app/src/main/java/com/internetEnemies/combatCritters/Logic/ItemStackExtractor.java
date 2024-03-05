package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

public class ItemStackExtractor implements IItemVisitor{
    private List<Card> cards = new ArrayList<>();
    private List<Pack> packs = new ArrayList<>();

    public ItemStackExtractor(List<ItemStack<?>> itemStackList) {
        for(ItemStack<?> itemStack : itemStackList) {
            itemStack.getItem().accept(this);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Pack> getPacks() {
        return packs;
    }
    @Override
    public void visitCritterCard(CritterCard card) {
        cards.add(card);
    }

    @Override
    public void visitItemCard(ItemCard card) {
        cards.add(card);
    }

    @Override
    public void visitPack(Pack pack) {
        packs.add(pack);
    }

    @Override
    public void visitCurrency(Currency currency) {}
}
