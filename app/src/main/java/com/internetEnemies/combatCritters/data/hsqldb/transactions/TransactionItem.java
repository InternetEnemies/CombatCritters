package com.internetEnemies.combatCritters.data.hsqldb.transactions;

/**
 * TransactionItem.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/2/24
 * 
 * @PURPOSE:    represents a transaction item db record
 */
public record TransactionItem(int tid, String type, boolean recv, int packId, int cardId, int currency, int amount) {
}
