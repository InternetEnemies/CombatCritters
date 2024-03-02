package com.internetEnemies.combatCritters.data.cardOrder;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.Comparator;

/**
 * ICardComparator.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-02
 *
 * @PURPOSE:    interface for comparing cards, used by List.sort
 */
public interface ICardComparator extends Comparator<ItemStack<Card>> {
    //I dont know if we actually need this class, I just got tired of typing "Comparator<ItemStack<Card>>"
    //either way:
    // ! Intentionally left empty, shorthand for the extension
}
