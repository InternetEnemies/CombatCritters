package com.internetEnemies.combatCritters.objects;

import java.util.ArrayList;
import java.util.List;

public class TransactionBuilder<T> implements ITransactionBuilder<T>{
    List<TransactionItem<T>> given;
    List<TransactionItem<T>> received;

    public TransactionBuilder(){
        reset();
    }
    @Override
    public void addToReceived(TransactionItem<T> item) {
        received.add(item);
    }

    @Override
    public void addToGiven(TransactionItem<T> item) {
        received.add(item);
    }

    @Override
    public Transaction<T> build() {
        return new Transaction<>(given, received);
    }

    @Override
    public void reset() {
        given = new ArrayList<>();
        received = new ArrayList<>();
    }
}
