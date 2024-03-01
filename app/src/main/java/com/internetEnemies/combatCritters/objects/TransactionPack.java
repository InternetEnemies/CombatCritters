package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.ITransactionVisitor;

public class TransactionPack extends TransactionItem<Pack>{
    public TransactionPack(Pack pack, int quantity){
        item = pack;
        this.quantity = quantity;
    }

    @Override
    public void acceptValidate(ITransactionVisitor visitor) {
        visitor.visitPack(this);
    }


    @Override
    public void acceptAdd(ITransactionVisitor visitor) {
        visitor.visitPack(this);
    }

    @Override
    public void acceptRemove(ITransactionVisitor visitor) {
        visitor.visitPack(this);
    }
}
