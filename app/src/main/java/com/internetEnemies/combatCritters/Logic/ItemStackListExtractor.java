package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.ArrayList;
import java.util.List;

/**
 * IItemStackListExtractor.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     06-March-2024
 *
 * @PURPOSE:     Extracts the Cards and Packs from a List<ItemStack<?>>
 */
public class ItemStackListExtractor implements IItemStackListExtractor, IItemVisitor{
    private final List<Card> cards = new ArrayList<>();
    private final List<Pack> packs = new ArrayList<>();
    private final List<Currency> currencies = new ArrayList<>();

    public ItemStackListExtractor(List<ItemStack<?>> itemStackList) {
        for(ItemStack<?> itemStack : itemStackList) {
            itemStack.getItem().accept(this);
        }
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public List<Pack> getPacks() {
        return packs;
    }
    @Override
    public List<Currency> getCurrencies() {return currencies;}
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
    public void visitCurrency(Currency currency) {currencies.add(currency);}
}
