package com.internetEnemies.combatCritters.data.hsqldb.transactions;

/**
 * TransactionItems.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/2/24
 * 
 * @PURPOSE:    Holder for items from both sides of Transactions Items
 */
public record TransactionItems<Tx, Rx>(Tx transmitItem, Rx receiveItem) {
}
