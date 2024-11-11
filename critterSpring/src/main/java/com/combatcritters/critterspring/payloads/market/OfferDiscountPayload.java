package com.combatcritters.critterspring.payloads.market;

import com.internetEnemies.combatCritters.objects.DiscountTransaction;

import java.util.List;

/**
 * OfferDiscountPayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/11/24
 * 
 * @PURPOSE:    OfferDiscount payload for rest
 */
public record OfferDiscountPayload(int discountid, int discount, List<OfferItemPayload<?>> discounted_give, OfferPayload parent_offer) {
    public static OfferDiscountPayload from(DiscountTransaction discountTransaction) {
        List<OfferItemPayload<?>> discounted_give = OfferItemPayload.listFrom(discountTransaction.discounted().getTransmit().getItems());
        OfferPayload parent = OfferPayload.from(discountTransaction.parent());
        return new OfferDiscountPayload(discountTransaction.discount(), discountTransaction.discount(), discounted_give, parent);
    }
    public static List<OfferDiscountPayload> listFrom(List<DiscountTransaction> discountTransactions) {
        return discountTransactions.stream().map(OfferDiscountPayload::from).toList();
    }
}
