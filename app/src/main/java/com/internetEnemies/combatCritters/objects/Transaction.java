package com.internetEnemies.combatCritters.objects;

import java.util.List;

/**
 * Transaction.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE: Transaction class to store items to be removed and added to the player's inventory.
 */

public class Transaction {

    public enum TransactionType {
        CARD, PACK, BUNDLE, DEAL, TRADE
    }

    //TODO: should these be private?
    List<ItemStack<?>> given;    //Item given to the marketplace from player
    List<ItemStack<?>> received; //Item received by player from the marketplace
    TransactionType transactionType;

    Transaction(List<ItemStack<?>> given, List<ItemStack<?>> received, TransactionType transactionType){
        this.given = given;
        this.received = received;
        this.transactionType = transactionType;
    }

    /**
     * Gets the list of all items being removed from the user's inventory
     * @return A list of TransactionItems.
     */
    public List<ItemStack<?>> getGiven(){
        return given;
    }
    /**
     * Gets the list of all items being added to the user's inventory
     * @return A list of TransactionItems.
     */
    public List<ItemStack<?>> getReceived(){
        return received;
    }

    /**
     * @return the first item in received. If received is empty return null.
     */
    public ItemStack<?> getReceivedFirstItem() {
        if(received.size() == 0) {
            return null;
        }
        return received.get(0);
    }

    /**
     * @return the first item in given. If given is empty return null.
     */
    public ItemStack<?> getGivenFirstItem() {
        if(received.size() == 0) {
            return null;
        }
        return given.get(0);
    }

    /**
     * @return following 5 methods return booleans on the transaction type.
     */
    public boolean isCard() {return transactionType == TransactionType.CARD;}

    public boolean isPack() {return transactionType == TransactionType.PACK;}

    public boolean isBundle() {return transactionType == TransactionType.BUNDLE;}

    public boolean isDeal() {return transactionType == TransactionType.DEAL;}

    public boolean isTrade() {return transactionType == TransactionType.TRADE;}
}
