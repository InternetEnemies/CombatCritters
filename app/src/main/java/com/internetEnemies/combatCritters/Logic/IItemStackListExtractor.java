package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public interface IItemStackListExtractor {
    /*
     * @return list of cards extracted from List<ItemStack<?>>
     */
    List<Card> getCards();

    /*
     * @return list of packs extracted from List<ItemStack<?>>
     */
    List<Pack> getPacks();
}
