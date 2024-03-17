package com.internetEnemies.combatCritters.objects;

import java.io.Serializable;
import java.util.List;

/**
 * Transaction.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE: Abstract Transaction class to store items to be removed and added to the player's inventory.
 */

public abstract class Transaction implements Serializable {
    int id;
    List<ItemStack<?>> received;

    public Transaction(int id, List<ItemStack<?>> received) {
        this.id = id;
        this.received = received;
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
}
