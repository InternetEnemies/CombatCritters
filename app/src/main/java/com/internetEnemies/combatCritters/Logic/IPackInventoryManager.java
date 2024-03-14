package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

/**
 * IPackInventoryManager.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-13
 *
 * @PURPOSE:    Interface for the PackInventoryManager, which handles the user's inventory of packs.
 */

public interface IPackInventoryManager {
    /**
     * Returns a list of all the packs currently in the inventory with duplicates separated.
     * @return
     */
    List<Pack> packsInInventory();

    /**
     * Opens a pack and removes it from the inventory.
     * @param pack the pack to be opened.
     * @return The list of cards being pulled.
     */
    List<Card> openPack(Pack pack);
}
