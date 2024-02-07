package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.Map;

public interface ICardSearch {
    /**
     * get map of owned cards and their quantities
     * @return map of cards and quantities
     */
    Map<Card,Integer> getOwned();

    /**
     * get map of all cards and their quantities owned (zero for not owned)
     * @return map of all cards and quantities
     */
    Map<Card,Integer> getAll();

    /**
     * get map of filtered cards and their quantities owned
     * @param filter the card rarity wanted to search
     * @return map of cards and quantities after filtered
     */
    Map<Card,Integer> getOwned(Card.Rarity filter);

    /**
     * get map of filtered cards and their quantities
     * @param filter the card rarity wanted to search
     * @return map of all cards and quantities after filtered
     */
    Map<Card,Integer> getAll(Card.Rarity filter);
}
