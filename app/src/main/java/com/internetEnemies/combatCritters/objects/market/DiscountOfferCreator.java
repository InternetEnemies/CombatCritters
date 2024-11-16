package com.internetEnemies.combatCritters.objects.market;

import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * DiscountOfferCreator.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/16/24
 * 
 * @PURPOSE:    object for discount offer creation
 */
public record DiscountOfferCreator(List<ItemStack<?>> discountedGive, int level, int parentId, int discount) {
}
