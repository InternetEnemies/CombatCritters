package com.internetEnemies.combatCritters.objects;
import java.util.ArrayList;
import java.util.List;

/**
 * TransactionBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    Transaction Builder class.
 */

public class TransactionBuilder implements ITransactionBuilder{
    List<TransactionItem> given;
    List<TransactionItem> received;

    public TransactionBuilder(){
        reset();
    }
    @Override
    public void addToReceived(TransactionItem item) {
        received.add(item);
    }

    @Override
    public void addToGiven(TransactionItem item) {
        received.add(item);
    }

    @Override
    public Transaction build() {
        return new Transaction(given, received);
    }

    @Override
    public void reset() {
        given = new ArrayList<>();
        received = new ArrayList<>();
    }
}
