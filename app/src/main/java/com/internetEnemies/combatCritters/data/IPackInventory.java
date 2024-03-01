package com.internetEnemies.combatCritters.data;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;
import java.util.Map;

/**
 * IPackInventory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    This interface handles the Packs that the player currently has in their inventory
 */
public interface IPackInventory {
    /**
     *
     * @param pack the Pack object to be counted
     * @return the number of packs of a certain type
     */
    int getPackAmount(Pack pack);

    /**
     * Add a new Pack to the inventory
     * @param pack pack to add
     */
    void addPack(Pack pack);

    /**
     * add multiple packs to the inventory
     * @param packs list of cards to add
     */
    void addPacks(List<Pack> packs);

    /**
     * removes a (one) pack from the inventory
     * @param pack type of card to remove
     */
    void removePack(Pack pack);

    /**
     * get an unmodifiable map of packs and their quantities
     * @return a map of packs and their amounts
     */
    Map<Pack,Integer> getPacks();
}
