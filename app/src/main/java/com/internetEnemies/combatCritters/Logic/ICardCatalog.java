/**
 * ICardCatalog.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-07
 *
 * @PURPOSE:    interface of filtering cards
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardFilter;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

public interface ICardCatalog {

    /**
     * get the cards resulting from the given filter and orderings
     * @param filter object with filter parameters
     * @param orders list of ordering types
     * @return list of card stacks
     */
    List<ItemStack<Card>> get(CardFilter filter, List<CardOrder> orders);
}
