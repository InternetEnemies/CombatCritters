package com.internetEnemies.combatCritters.data.cardOrder;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;


/**
 * ById.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    compare Stacks of cards based on the id of the card
 */
public class ById implements ICardComparator {
    @Override
    public int compare(ItemStack<Card> o1, ItemStack<Card> o2) {
        return Integer.compare(o1.getItem().getId(), o2.getItem().getId());
    }
}
