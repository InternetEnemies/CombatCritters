package com.internetEnemies.combatCritters.data.cardOrder;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;


public class ById implements ICardComparator {
    @Override
    public int compare(ItemStack<Card> o1, ItemStack<Card> o2) {
        return Integer.compare(o1.getItem().getId(), o2.getItem().getId());
    }
}
