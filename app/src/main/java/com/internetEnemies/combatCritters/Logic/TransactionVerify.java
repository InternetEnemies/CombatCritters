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

import com.internetEnemies.combatCritters.Logic.visitor.IItemVisitor;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

public class TransactionVerify implements IItemVisitor {
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;

    private boolean isValid;

    private final int quantity;


    public TransactionVerify(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank, int quantity){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
        this.quantity = quantity;
        isValid = true;
    }

    /**
     * Checks if the player has the necessary amount of the given card
     * @param card the card to be checked.
     */
    @Override
    public void visitCritterCard(CritterCard card) {
        if (cardInventory.getCardAmount(card) < quantity){
            isValid = false;
        }
    }
    /**
     * Checks if the player has the necessary amount of the given card
     * @param card the card to be checked.
     */
    @Override
    public void visitItemCard(ItemCard card) {
        if (cardInventory.getCardAmount(card) < quantity){
            isValid = false;
        }
    }
    /**
     * Checks if the player has the necessary amount of the given Pack
     * @param pack the pack to be checked.
     */
    @Override
    public void visitPack(Pack pack) {
        if (packInventory.getPackAmount(pack) < quantity){
            isValid = false;
        }
    }
    /**
     * Checks if the player has the necessary amount of the given currency
     * @param currency the currency quantity to be checked.
     */
    @Override
    public void visitCurrency(Currency currency) {
        if (bank.getCurrentBalance().getAmount() < currency.getAmount()){
            isValid = false;
        }
    }
    /**
     * Verifies if the user has the necessary items in their inventory
     * @return true if the transaction is valid, false if it isnt.
     */
    public boolean isValid(){
        return isValid;
    }

}
