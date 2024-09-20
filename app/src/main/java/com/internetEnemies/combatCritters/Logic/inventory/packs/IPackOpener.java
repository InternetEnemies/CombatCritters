/**
 * IPackOpener.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-11
 *
 * @PURPOSE:    interface for opening pack
 */

package com.internetEnemies.combatCritters.Logic.inventory.packs;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public interface IPackOpener {
    /**
     * open a pack and send its contents to the players inventory
     *
     * @param pack Pack to open from
     * @return the list of cards that will be added
     */
    List<Card> openPack(Pack pack);
}
