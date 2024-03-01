package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.ITransactionVisitor;

public abstract class TransactionItem<T>  {
    protected T item;
    protected int quantity;

    public T getItem(){
        return item;
    }
    public int getQuantity() {
        return quantity;
    }
    /**
     * Validates that the item(s) exists within the player's inventory via the visitor interface.
     * @return an instantiated Transaction object.
     *
     */
    public abstract void acceptValidate(ITransactionVisitor visitor);
    /**
     * Performs the proper logic to add to a respective inventory by type via the visitor interface.
     *
     */
    public abstract void acceptAdd(ITransactionVisitor visitor);
    /**
     * Performs the proper logic to remove from a respective inventory by type via the visitor interface.
     *
     */
    public abstract void acceptRemove(ITransactionVisitor visitor);
}
