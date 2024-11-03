package com.combatcritters.critterspring.payloads.market;

import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.VendorTransaction;

import java.util.List;

/**
 * OfferPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/3/24
 * 
 * @PURPOSE:    Offer payload def for REST
 */
public record OfferPayload(Integer id, List<OfferItemPayload<?>> give, OfferItemPayload<?> receive) {
    public static OfferPayload from(VendorTransaction transaction) {
        ItemStack<?> receive = transaction.getReceive().getItem();
        List<ItemStack<?>> give = transaction.getTransmit().getItems();
        
        return new OfferPayload(
                transaction.getId(),
                OfferItemPayload.listFrom(give),
                OfferItemPayload.from(receive)
        );
    }
}
