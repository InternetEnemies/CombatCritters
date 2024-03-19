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
        if(received.isEmpty()) {
            return null;
        }
        return received.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return this.id == that.id;
    }

    /**
     * get the id of the transaction
     * @return id of the transaction
     */
    public int getId() {
        return this.id;
    }
}
