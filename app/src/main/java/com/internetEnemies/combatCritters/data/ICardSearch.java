package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

public interface ICardSearch {
    /**
     * get map of owned cards and their quantities
     * @return list of ItemsStacks of owned cards
     */
    List<ItemStack<Card>> getOwned();

    /**
     * get map of all cards and their quantities owned (zero for not owned)
     * @return list of ItemsStacks all cards
     */
    List<ItemStack<Card>> getAll();
}
