package com.internetEnemies.combatCritters.data.cardOrder;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.Comparator;

public interface ICardComparator extends Comparator<ItemStack<Card>> {
    //I dont know if we actually need this class, I just got tired of typing "Comparator<ItemStack<Card>>"
    //either way:
    // ! Intentionally left empty, shorthand for the extension
}
