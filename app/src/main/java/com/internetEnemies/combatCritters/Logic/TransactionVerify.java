package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.Transaction;

import java.util.List;

public class TransactionVerify implements IItemVisitor{
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;

    private boolean isValid;

    private int currQuantity;


    TransactionVerify(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
        isValid = true;
        currQuantity = 1;
    }
    @Override
    public void visitCritterCard(CritterCard card) {
        if (cardInventory.getCardAmount(card) < currQuantity){
            isValid = false;
        }
    }

    @Override
    public void visitItemCard(ItemCard card) {
        if (cardInventory.getCardAmount(card) < currQuantity){
            isValid = false;
        }
    }

    @Override
    public void visitPack(Pack pack) {
        if (packInventory.getPackAmount(pack) < currQuantity){
            isValid = false;
        }
    }

    @Override
    public void visitCurrency(Currency currency) {
        if (bank.getCurrentBalance(currency.getId()).getAmount() < currency.getAmount()){
            isValid = false;
        }

    }

    public boolean verifyTransaction(Transaction transaction){
        List<ItemStack<?>> toBeRemoved = transaction.getGiven();

        for (ItemStack<?> item: toBeRemoved) {
            currQuantity = item.getAmount();
            item.getItem().accept(this);
        }

        if (isValid){
            TransactionAdd adder = new TransactionAdd(cardInventory, packInventory, bank);
            adder.addItems(transaction.getReceived());



        }
        return isValid;
    }
}
