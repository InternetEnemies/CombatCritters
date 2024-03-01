package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.ITransactionVisitor;

public class TransactionCard extends TransactionItem<Card>{
    public TransactionCard(Card card, int quantity){
        item = card;
        this.quantity = quantity;
    }

    @Override
    public void acceptValidate(ITransactionVisitor visitor) {
        visitor.visitCard(this);
    }

    @Override
    public void acceptAdd(ITransactionVisitor visitor) {
        visitor.visitCard(this);

    }

    @Override
    public void acceptRemove(ITransactionVisitor visitor) {
        visitor.visitCard(this);

    }
}
