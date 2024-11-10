package com.internetEnemies.combatCritters.objects;

/**
 * DiscountTransaction.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/10/24
 * 
 * @PURPOSE:    represents a discounted transaction
 */
public record DiscountTransaction(VendorTransaction parent, VendorTransaction discounted, int discount) { 
}
