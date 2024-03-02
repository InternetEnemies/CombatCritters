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
    List<ItemStack<?>> given;
    List<ItemStack<?>> received;

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


}
