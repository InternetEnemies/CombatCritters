package com.internetEnemies.combatCritters.data.cardOrder;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

public class ByPlayCost implements ICardComparator{
    @Override
    public int compare(ItemStack<Card> o1, ItemStack<Card> o2) {
        return Integer.compare(o1.getItem().getPlayCost(), o2.getItem().getPlayCost());
    }
}
