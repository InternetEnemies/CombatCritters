package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;
import java.util.Map;

public interface ICardSearch {
    /**
     * get map of owned cards and their quantities
     * @return map of cards and quantities
     */
    List<ItemStack<Card>> getOwned();

    /**
     * get map of all cards and their quantities owned (zero for not owned)
     * @return map of all cards and quantities
     */
    List<ItemStack<Card>> getAll();
}
