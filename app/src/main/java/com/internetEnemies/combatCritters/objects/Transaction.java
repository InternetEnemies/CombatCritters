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
    List<ItemStack<?>> given;    //Item given to the marketplace from player
    List<ItemStack<?>> received; //Item received by player from the marketplace

    Transaction(List<ItemStack<?>> given, List<ItemStack<?>> received){
        this.given = given;
        this.received = received;
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
        return received.get(0);
    }

}
