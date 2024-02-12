package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardOrder;

import java.util.List;
import java.util.Map;

/**
 * Builder for card database search queries
 */
public interface ICardSearch {
    /**
     * get the result of the search
     * @return Map of cards and quantities
     */
    Map<Card,Integer> get();

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
