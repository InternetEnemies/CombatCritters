package com.internetEnemies.combatCritters.data.cardOrder;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

/**
 * ByPlayCost.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    compare stacks of cards based on the play cost of the card
 */
public class ByPlayCost implements ICardComparator{
    @Override
    public int compare(ItemStack<Card> o1, ItemStack<Card> o2) {
        return Integer.compare(o1.getItem().getPlayCost(), o2.getItem().getPlayCost());
    }
}
