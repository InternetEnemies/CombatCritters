package com.internetEnemies.combatCritters.Logic;

/**
 * TransactionHandler.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    Handles incoming transactions and decides whether or not a transaction
 * is valid, and performs the necessary operations if it is.
 */

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

public class TransactionHandler implements IItemVisitor{
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;

    private boolean isValid;

    private int currQuantity;


    public TransactionHandler(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
        isValid = true;
        currQuantity = 1;
    }

    /**
     * Checks if the player has the necessary amount of the given card
     * @param card the card to be checked.
     */
    @Override
    public void visitCritterCard(CritterCard card) {
        if (cardInventory.getCardAmount(card) < currQuantity){
            isValid = false;
        }
    }
    /**
     * Checks if the player has the necessary amount of the given card
     * @param card the card to be checked.
     */
    @Override
    public void visitItemCard(ItemCard card) {
        if (cardInventory.getCardAmount(card) < currQuantity){
            isValid = false;
        }
    }
    /**
     * Checks if the player has the necessary amount of the given Pack
     * @param pack the pack to be checked.
     */
    @Override
    public void visitPack(Pack pack) {
        if (packInventory.getPackAmount(pack) < currQuantity){
            isValid = false;
        }
    }
    /**
     * Checks if the player has the necessary amount of the given currency
     * @param currency the currency quantity to be checked.
     */
    @Override
    public void visitCurrency(Currency currency) {
        if (bank.getCurrentBalance(currency.getId()).getAmount() < currency.getAmount()){
            isValid = false;
        }

    }
    /**
     * Verifies if the user has the necessary items in their inventory
     * and performs the operations needed for the transaction if they do.
     * @param transaction The transaction being processed.
     */
    public boolean verifyTransaction(Transaction transaction){
        List<ItemStack<?>> toBeRemoved = transaction.getGiven();

        for (ItemStack<?> item: toBeRemoved) {
            currQuantity = item.getAmount();
            item.getItem().accept(this);
            System.out.println(isValid);
        }

        if (isValid){
            TransactionAdd adder = new TransactionAdd(cardInventory, packInventory, bank);
            TransactionRemove remover = new TransactionRemove(cardInventory, packInventory, bank);
            adder.addItems(transaction.getReceived());
            remover.removeItems(transaction.getGiven());
        }
        return isValid;
    }
}
