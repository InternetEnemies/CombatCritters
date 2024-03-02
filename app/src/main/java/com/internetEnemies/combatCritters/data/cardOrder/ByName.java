package com.internetEnemies.combatCritters.data.cardOrder;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;


/**
 * ByName.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    compare stacks of cards based on the cards name
 */
public class ByName implements ICardComparator {

    @Override
    public int compare(ItemStack<Card> o1, ItemStack<Card> o2) {
        return  o1.getItem().getName().compareTo(o2.getItem().getName());
    }
}
