package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.Map;

public interface ICardCatalog {
    /**
     * get cards from the players inventory
     * @return map of cards and their quantities
     */
    Map<Card, Integer> getOwned();

    /**
     * get all cards owned and not owned with counts
     * @return map of cards and their quantities
     */
    Map<Card, Integer> getAll();
}
