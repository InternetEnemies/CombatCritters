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

    String name;
    String image;
    private final List<ItemStack<?>> given;

    public TradeTransaction(int id, List<ItemStack<?>> received, List<ItemStack<?>> given, String name, String image){
        super(id, received);
        this.given = given;
        this.name = name;
        this.image = image;
    }

    /**
     *
     * @return the list of items being added to the user's inventory
     */
    public List<ItemStack<?>> getGiven(){
        return given;
    }
    public String getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
}
