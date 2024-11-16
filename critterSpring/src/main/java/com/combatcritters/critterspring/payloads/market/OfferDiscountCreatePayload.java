package com.combatcritters.critterspring.payloads.market;

import com.combatcritters.critterspring.payloads.itemConverter.IItemConverter;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.market.DiscountOfferCreator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OfferDiscountCreatePayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    Creation object for creating discounts on existing offers
 */
public record OfferDiscountCreatePayload(List<OfferCreationItemPayload> discounted_give, int level, int offerid, int discount) {
    public DiscountOfferCreator toDiscountOfferCreator(IItemConverter converter) {
        List<ItemStack<?>> items = discounted_give.stream().map(converter::getItem).collect(Collectors.toUnmodifiableList());
        return new DiscountOfferCreator(items, level, offerid, discount);
    }
}
