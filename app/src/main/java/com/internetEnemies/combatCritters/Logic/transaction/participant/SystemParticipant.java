package com.internetEnemies.combatCritters.Logic.transaction.participant;

import com.internetEnemies.combatCritters.objects.ItemStack;

/**
 * SystemParticipant.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    Represents the system in transactions
 */
public class SystemParticipant implements IParticipant {
    @Override
    public void add(ItemStack<?> item) {} // system doesn't have an inventory to add to 

    @Override
    public void remove(ItemStack<?> item) {} // system doesn't have an inventory to remove from

    @Override
    public boolean has(ItemStack<?> item) {
        return true; // System has all
    }
}
