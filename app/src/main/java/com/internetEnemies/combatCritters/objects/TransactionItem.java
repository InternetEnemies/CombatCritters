package com.internetEnemies.combatCritters.objects;

/**
 * TransactionItem.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    Stores different item objects to be used within a Transaction object.
 */

public class TransactionItem {
    protected IVisitableItem item;
    protected int quantity;
    public TransactionItem(IVisitableItem item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Gets the item stored in item.
     * @return an object that implements IVisitableItem
     */
    public IVisitableItem getItem(){
        return item;
    }
    /**
     * Gets the quantity of the item stored in item.
     * @return single integer value for the quantity.
     */
    public int getQuantity() {
        return quantity;
    }

}
