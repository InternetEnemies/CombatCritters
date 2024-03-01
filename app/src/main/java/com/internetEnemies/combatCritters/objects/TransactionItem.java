package com.internetEnemies.combatCritters.objects;

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
