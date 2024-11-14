package com.combatcritters.critterspring.payloads.itemConverter;

import com.combatcritters.critterspring.payloads.market.OfferCreationItemPayload;
import com.internetEnemies.combatCritters.objects.ItemStack;

/**
 * IItemConverter.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    interface for converting items to item stacks
 */
public interface IItemConverter {
    /**
     * get an itemstack from an item payload
     * @param itemPayload payload to convert
     * @return ItemStack from the payload
     */
    ItemStack<?> getItem(OfferCreationItemPayload itemPayload);
}
