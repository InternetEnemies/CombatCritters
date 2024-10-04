/**
 * ICardSearch.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-07
 *
 * @PURPOSE:    Builder for card database search queries
 */

package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

public interface ICardSearch {
    /**
     * get map of owned cards and their quantities
     * @return list of ItemsStacks of owned cards
     */
    List<ItemStack<Card>> get(List<CardOrder> orders, CardFilter filter);
}
