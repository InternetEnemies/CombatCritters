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


    public abstract void acceptValidate(ITransactionVisitor visitor);

    public abstract void acceptAdd(ITransactionVisitor visitor);
    public abstract void acceptRemove(ITransactionVisitor visitor);
}
