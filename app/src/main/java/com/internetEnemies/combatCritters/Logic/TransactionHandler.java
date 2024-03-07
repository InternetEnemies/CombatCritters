package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.Transaction;

/**
 * TransactionHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-06
 *
 * @PURPOSE:    Handles all incoming transactions.
 */

//TODO override?
public class TransactionHandler implements ITransactionHandler{
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;

    public TransactionHandler(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
    }

    public boolean performTransaction(MarketTransaction transaction){
        if (verifyTransaction(transaction)){
            addItems(transaction);
            TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, bank, 1);
            transaction.getPrice().accept(remover);
        }

        return verifyTransaction(transaction);
    }

    public boolean performTransaction(TradeTransaction transaction){
        if (verifyTransaction(transaction)){
            addItems(transaction);
            for (ItemStack<?> item: transaction.getGiven()) {
                TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, bank, item.getAmount());
                item.getItem().accept(remover);
            }
        }

        return verifyTransaction(transaction);
    }

    public boolean verifyTransaction(TradeTransaction transaction){
        boolean isValid = true;
        for (ItemStack<?> item : transaction.getGiven()) {
            TransactionVerify verification = new TransactionVerify(cardInventory, packInventory, bank, item.getAmount());
            item.getItem().accept(verification);
            isValid &= verification.isValid();;
        }
        return isValid;
    }

    public boolean verifyTransaction(MarketTransaction transaction){
        TransactionVerify verification = new TransactionVerify(cardInventory, packInventory, bank, 1);
        transaction.getPrice().accept(verification);
        return verification.isValid();
    }

    public void addItems(Transaction transaction){
        for (ItemStack<?> item: transaction.getReceived()) {
            TransactionAdd adder = new TransactionAdd(cardInventory, packInventory, bank, item.getAmount());
            item.getItem().accept(adder);
        }
    }


}
