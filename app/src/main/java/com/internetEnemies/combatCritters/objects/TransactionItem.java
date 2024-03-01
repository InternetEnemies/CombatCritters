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
    public IVisitableItem getItem(){
        return item;
    }
    public int getQuantity() {
        return quantity;
    }

}
