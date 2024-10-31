package com.internetEnemies.combatCritters.Logic.transaction.participant;

import com.internetEnemies.combatCritters.objects.ItemStack;

/**
 * IInventoryProvider.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    unified interface for participant inventories
 */
public interface IParticipant {
    void add(ItemStack<?> item);
    void remove(ItemStack<?> item);
    boolean has(ItemStack<?> item);
}
