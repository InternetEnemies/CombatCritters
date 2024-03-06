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

public class TradeTransactionBuilder implements ITradeTransactionBuilder {
    List<ItemStack<?>> given;
    List<ItemStack<?>> received;

    public TradeTransactionBuilder(){
        reset();
    }
    @Override
    public void addToReceived(ItemStack<?> item) {
        received.add(item);
    }

    @Override
    public void addToGiven(ItemStack<?> item) {
        given.add(item);
    }

    @Override
    public TradeTransaction build() {
        return new TradeTransaction(received, given);
    }

    @Override
    public void reset() {
        given = new ArrayList<>();
        received = new ArrayList<>();
    }
}
