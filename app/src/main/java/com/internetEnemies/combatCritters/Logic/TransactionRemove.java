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

public class TransactionRemove implements IItemVisitor{
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory bank;
    private int currQuantity;


    public TransactionRemove(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory bank){
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.bank = bank;
        currQuantity = 1;
    }
    @Override
    public void visitCritterCard(CritterCard card) {
        cardInventory.removeCard(card, currQuantity);
    }

    @Override
    public void visitItemCard(ItemCard card) {
        cardInventory.removeCard(card, currQuantity);
    }

    @Override
    public void visitPack(Pack pack) {
        packInventory.removeCard(pack, currQuantity);
    }

    @Override
    public void visitCurrency(Currency currency) {
        if (bank.getCurrentBalance(currency.getId()).getAmount() - currency.getAmount() > 0){
            bank.removeFromBalance(currency, currency.getId());
        }
        else {
            bank.setBalance(new Currency(0, currency.getId()), currency.getId());
        }

    }
    public void removeItems(List<ItemStack<?>> removedItems){
        for (ItemStack<?> item: removedItems) {
            currQuantity = item.getAmount();
            item.getItem().accept(this);
        }

    }
}
