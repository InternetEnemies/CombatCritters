package com.internetEnemies.combatCritters.Logic.transaction;

import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.MultiReceiveTransaction;

/**
 * TransactionHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-06
 *
 * @PURPOSE:    Handles all incoming transactions.
 */

public class TransactionHandler implements ITransactionHandler {
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;
    public TransactionHandler(){
        cardInventory = Database.getInstance().getCardInventory();
        packInventory = Database.getInstance().getPackInventory();
        bank = Database.getInstance().getCurrencyInventory();
    }

    public TransactionHandler(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
    }

    public boolean performTransaction(MarketTransaction transaction){
        boolean isValid = false;
        if (verifyTransaction(transaction)){
            isValid = true;
            addItems(transaction);
            TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, bank, 1);
            transaction.getPrice().accept(remover);
        }

        return isValid;
    }

    public boolean performTransaction(TradeTransaction transaction){
        boolean flag = verifyTransaction(transaction);
        if (flag){
            addItems(transaction);
            for (ItemStack<?> item: transaction.getGiven()) {
                TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, bank, item.getAmount());
                item.getItem().accept(remover);
            }
        }

        return flag;
    }

    public boolean verifyTransaction(TradeTransaction transaction){
        boolean isValid = true;
        for (ItemStack<?> item : transaction.getGiven()) {
            TransactionVerify verification = new TransactionVerify(cardInventory, packInventory, bank, item.getAmount());
            item.getItem().accept(verification);
            isValid &= verification.isValid();
        }
        return isValid;
    }

    public boolean verifyTransaction(MarketTransaction transaction){
        TransactionVerify verification = new TransactionVerify(cardInventory, packInventory, bank, 1);
        transaction.getPrice().accept(verification);
        return verification.isValid();
    }

    public void addItems(MultiReceiveTransaction transaction){
        for (ItemStack<?> item: transaction.getReceived()) {
            TransactionAdd adder = new TransactionAdd(cardInventory, packInventory, bank, item.getAmount());
            item.getItem().accept(adder);
        }
    }


}
