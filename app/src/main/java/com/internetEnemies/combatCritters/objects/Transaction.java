package com.internetEnemies.combatCritters.objects;

import java.util.List;

public class Transaction {
    List<TransactionItem> given;
    List<TransactionItem> received;

    Transaction(List<TransactionItem> given, List<TransactionItem> received){
        this.given = given;
        this.received = received;
    }
    public List<TransactionItem> getGiven(){
        return given;
    }
    public List<TransactionItem> getReceived(){
        return received;
    }


}
