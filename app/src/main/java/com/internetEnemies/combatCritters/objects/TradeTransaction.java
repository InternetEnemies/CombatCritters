package com.internetEnemies.combatCritters.objects;

import java.util.List;

/**
 * TradeTransaction.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    Object for transactions the happen as a result of a trade.
 */

public class TradeTransaction extends Transaction{
    private List<ItemStack<?>> given;

    public TradeTransaction(List<ItemStack<?>> received, List<ItemStack<?>> given){
        this.received = received;
        this.given = given;
    }

    /**
     *
     * @return the list
     */
    public List<ItemStack<?>> getGiven(){
        return given;
    }
}
