package com.internetEnemies.combatCritters.objects;

import java.util.List;

public class Transaction<T> {
    List<TransactionItem<T>> given;
    List<TransactionItem<T>> received;

    Transaction(List<TransactionItem<T>> given, List<TransactionItem<T>> received){
        this.given = given;
        this.received = received;
    }
    public List<TransactionItem<T>> getGiven(){
        return given;
    }
    public List<TransactionItem<T>> getReceived(){
        return received;
    }


}
