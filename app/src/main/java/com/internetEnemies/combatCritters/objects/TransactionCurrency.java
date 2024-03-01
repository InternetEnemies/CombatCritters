package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.ITransactionVisitor;

public class TransactionCurrency extends TransactionItem<Currency>{
    public TransactionCurrency(Currency amount, int quantity){
        item = amount;
        this.quantity = quantity;
    }

    @Override
    public void acceptValidate(ITransactionVisitor visitor) {
        visitor.visitCurrency(this);
    }

    @Override
    public void acceptAdd(ITransactionVisitor visitor) {
        visitor.visitCurrency(this);
    }

    @Override
    public void acceptRemove(ITransactionVisitor visitor) {
        visitor.visitCurrency(this);
    }
}
