package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemCard;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public class TransactionAdd implements IItemVisitor{
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;
    private int currQuantity;


    public TransactionAdd(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
        currQuantity = 1;
    }

    @Override
    public void visitCritterCard(CritterCard card) {
        for (int i = 0; i < currQuantity; i++){
            cardInventory.addCard(card);
        }
    }

    @Override
    public void visitItemCard(ItemCard card) {
        for (int i = 0; i < currQuantity; i++){
            cardInventory.addCard(card);
        }
    }

    @Override
    public void visitPack(Pack pack) {
        for (int i = 0; i < currQuantity; i++){
            packInventory.addPack(pack);
        }
    }

    @Override
    public void visitCurrency(Currency currency) {
        bank.addtoBalance(currency, currency.getId());
    }

    public void addItems(List<ItemStack<?>> addedItems){
        for (ItemStack<?> item: addedItems) {
            currQuantity = item.getAmount();
            item.getItem().accept(this);
        }
    }
}
