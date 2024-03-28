package com.internetEnemies.combatCritters.Logic;
import com.internetEnemies.combatCritters.objects.ITradeTransactionBuilder;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;

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
    private int id;

    private String name;
    private String image;
    private List<ItemStack<?>> given;
    private List<ItemStack<?>> received;

    public TradeTransactionBuilder(){
        reset();
    }

    @Override
    public void setID(int id) {
        this.id = id;
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
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public TradeTransaction build() {
        return new TradeTransaction(id, received, given, name,image);
    }

    @Override
    public void reset() {
        given = new ArrayList<>();
        received = new ArrayList<>();
    }

    @Override
    public void fromTransaction(TradeTransaction transaction) {
        setID(transaction.getId());
        setImage(transaction.getImage());
        setName(transaction.getName());
        for(ItemStack<?> stack : transaction.getReceived()) {
            addToReceived(stack);
        }
        for(ItemStack<?> stack : transaction.getGiven()) {
            addToGiven(stack);
        }
    }
}
