package com.combatcritters.critterspring.payloads.market;

import java.util.List;

/**
 * OfferDiscountCreatePayload.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/14/24
 * 
 * @PURPOSE:    Creation object for creating discounts on existing offers
 */
public record OfferDiscountCreatePayload(List<OfferItemPayload<?>> discounted_give, int offerid, int discount) {
}
