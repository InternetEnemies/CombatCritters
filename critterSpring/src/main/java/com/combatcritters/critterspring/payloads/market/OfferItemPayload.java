package com.combatcritters.critterspring.payloads.market;

import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * OfferItemPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    OfferItem Payload for rest
 */
public record OfferItemPayload<T>(int count, T item, String type) { 
    public static final String TYPE_CARD = "card";
    public static final String TYPE_PACK = "pack";
    public static final String TYPE_CURRENCY = "currency";
    
    public static OfferItemPayload<?> from(ItemStack<?> stack) {
        OfferItemVisitor visitor = new OfferItemVisitor(stack.getAmount());
        stack.getItem().accept(visitor);
        return visitor.getResult();
    }
    
    public static List<OfferItemPayload<?>> listFrom(List<ItemStack<?>> items) {
        List<OfferItemPayload<?>> offerItems = new ArrayList<>();
        for (ItemStack<?> item : items) {
            offerItems.add(from(item));
        }
        return offerItems;
    }
}
