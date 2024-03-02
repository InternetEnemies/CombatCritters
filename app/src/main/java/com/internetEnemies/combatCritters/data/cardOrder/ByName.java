package com.internetEnemies.combatCritters.data.cardOrder;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;


public class ByName implements ICardComparator {

    @Override
    public int compare(ItemStack<Card> o1, ItemStack<Card> o2) {
        return  o1.getItem().getName().compareTo(o2.getItem().getName());
    }
}
