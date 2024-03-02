package com.internetEnemies.combatCritters.objects;
import java.util.ArrayList;
import java.util.List;

/**
 * TransactionBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-01
 *
 * @PURPOSE:    Builder class for Transaction objects.
 */

public class TransactionBuilder implements ITransactionBuilder{
    List<IItem> given;
    List<IItem> received;

    public TransactionBuilder(){
        reset();
    }
    @Override
    public void addToReceived(IItem item) {
        received.add(item);
    }

    @Override
    public void addToGiven(IItem item) {
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
