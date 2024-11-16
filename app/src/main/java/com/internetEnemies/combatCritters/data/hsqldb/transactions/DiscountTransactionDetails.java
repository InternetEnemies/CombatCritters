package com.internetEnemies.combatCritters.data.hsqldb.transactions;

/**
 * DiscountTransactionDetails.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/10/24
 * 
 * @PURPOSE:    holds the details of a discount
 */
public record DiscountTransactionDetails(int tid, int parent, int discount) {
}
