package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;
import java.util.Map;

public interface ICardCatalog {
    /**
     * get cards from the players inventory
     * @return list of cards and their quantities
     */
    List<ItemStack<Card>> getOwned();

    /**
     * get all cards owned and not owned with counts
     * @return list of cards and their quantities
     */
    List<ItemStack<Card>> getAll();
}
