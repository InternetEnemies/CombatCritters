package com.internetEnemies.combatCritters.objects;

import com.internetEnemies.combatCritters.Logic.transaction.transactionItem.ITransactionItem;

public abstract class Transaction <Tx extends ITransactionItem, Rx extends ITransactionItem> {
    private final int id;
    private final Tx tx;
    private final Rx rx;
    
    public Transaction(int id, Tx tx, Rx rx) {
        this.id = id;
        this.tx = tx;
        this.rx = rx;
    }
    public int getId() {
        return this.id;
    }
    
    public Tx getTransmit() {
        return this.tx;
    }
    
    public Rx getReceive() {
        return this.rx;
    }
}
