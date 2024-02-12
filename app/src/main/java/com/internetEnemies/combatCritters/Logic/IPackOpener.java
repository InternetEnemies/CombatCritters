package com.internetEnemies.combatCritters.Logic;

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
