package com.internetEnemies.combatCritters.objects.market;

import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * VendorOfferCreator.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    offer creation object
 */
public record VendorOfferCreator(ItemStack<?> rx, List<ItemStack<?>> tx, int level) {
}
