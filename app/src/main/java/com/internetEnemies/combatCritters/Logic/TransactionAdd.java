package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;


/**
 * TransactionAdd.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    Adds different objects stored within ItemStacks using it's respective
 * database interface.
 */

public class TransactionAdd implements IItemVisitor{
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;
    private final int quantity;


    public TransactionAdd(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank, int quantity){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
        this.quantity = quantity;
    }
    /**
     * Adds a certain number of cards to the user's inventory.
     * @param card the card to be added to the user's inventory
     */
    private void addCards(Card card){
        for (int i = 0; i < quantity; i++){
            cardInventory.addCard(card);
        }
    }
    @Override
    public void visitCritterCard(CritterCard card) {
        addCards(card);
    }
    /**
     * Adds a certain number of cards to the user's inventory.
     * @param card the card to be added to the user's inventory
     */

    @Override
    public void visitItemCard(ItemCard card) {
        addCards(card);
    }
    /**
     * Adds a certain number of packs to the user's inventory.
     * @param pack the pack to be added to the user's inventory
     */

    @Override
    public void visitPack(Pack pack) {
        for (int i = 0; i < quantity; i++){
            packInventory.addPack(pack);
        }
    }
    /**
     * Adds the currency given to the user's inventory.
     * @param currency the currency to be added to the user's balance.
     */
    @Override
        public void visitCurrency(Currency currency) {
            bank.addToBalance(currency);
        }

}
