package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardOrder;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * Builder for card database search queries
 */
public interface ICardSearch {
    /**
     * get map of owned cards and their quantities
     * @return list of ItemsStacks of owned cards
     */
    List<ItemStack<Card>> get();

    /**
     * get map of all cards and their quantities owned (zero for not owned)
     * @return list of ItemsStacks all cards
     */
    List<ItemStack<Card>> getAll();
    /**
     * add an order to the search (this can be called more than once)
     * @param order order to order by
     */
    void addOrder(CardOrder order);

    /**
     * get the filter builder for this CardSearch object
     * @return builder for a card filter
     */
    ICardFilterBuilder getFilterBuilder();
}
