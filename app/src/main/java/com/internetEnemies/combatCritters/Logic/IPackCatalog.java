/**
 * IPackCatalog.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-11
 *
 * @PURPOSE:    interface managing multiple packs
 */

package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public interface IPackCatalog {
    /**
     * get the pack with the given id
     * @param id id of pack to get
     * @return Pack object with the given id
     */
    Pack getPack(int id);

    /**
     * get the full list of packs
     * @return List of Pack's
     */
    List<Pack> getListOfPacks();
}
