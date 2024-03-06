package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

/**
 * TransactionRemove.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    removes different objects stored within ItemStacks using it's respective
 * database interface.
 */

public class TransactionRemove implements IItemVisitor{
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;
    private final int currQuantity;

    public TransactionRemove(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank, int currQuantity){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
        this.currQuantity = currQuantity;
    }

    /**
     * Removes a certain number of cards from the user's inventory.
     * @param card the card to be removed.
     */
    @Override
    public void visitCritterCard(CritterCard card) {
        removeCard(card);
    }
    /**
     * Removes a certain number of cards from the user's inventory.
     * @param card the card to be removed.
     */

    @Override
    public void visitItemCard(ItemCard card) {
        removeCard(card);
    }
    /**
     * Removes a certain number of packs from the user's inventory.
     * @param pack the pack to be removed.
     */

    @Override
    public void visitPack(Pack pack) {
        packInventory.removePack(pack, currQuantity);
    }

    /**
     * Subtracs a certain amount of currency from the user's inventory. If the value
     * is negative, set the user's currency value to 0.
     * @param currency the value of the amount being reduced.
     */
    @Override
    public void visitCurrency(Currency currency) {
        if (bank.getCurrentBalance(0).getAmount() - currency.getAmount() > 0){
            bank.removeFromBalance(currency, 0);
        }

    }

    private void removeCard(Card card){
        cardInventory.removeCard(card, currQuantity);
    }


}
