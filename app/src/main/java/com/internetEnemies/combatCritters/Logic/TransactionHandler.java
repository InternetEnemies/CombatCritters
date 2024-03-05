package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.objects.Transaction;

public class TransactionHandler {
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;
    private int currQuantity;


    public TransactionHandler(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
    }

    public void performTransaction(MarketTransaction transaction){
        TransactionVerify verification = new TransactionVerify(cardInventory, packInventory, bank);

        if (verification.verifyTransaction(transaction)){
            TransactionAdd adder = new TransactionAdd(cardInventory, packInventory, bank);
            TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, bank);
            adder.addItems(transaction.getReceived());
            transaction.getPrice().accept(remover);
        }
    }

    public void performTransaction(TradeTransaction transaction){
        TransactionVerify verification = new TransactionVerify(cardInventory, packInventory, bank);

        if (verification.verifyTransaction(transaction)){
            TransactionAdd adder = new TransactionAdd(cardInventory, packInventory, bank);
            TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, bank);
            adder.addItems(transaction.getReceived());
            remover.removeItems(transaction.getGiven());
        }

    }


}
