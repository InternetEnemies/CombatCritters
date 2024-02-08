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
}
