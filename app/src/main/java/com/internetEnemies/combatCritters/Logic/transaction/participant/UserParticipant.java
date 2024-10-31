package com.internetEnemies.combatCritters.Logic.transaction.participant;

import com.internetEnemies.combatCritters.Logic.transaction.TransactionAdd;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionRemove;
import com.internetEnemies.combatCritters.Logic.transaction.TransactionVerify;
import com.internetEnemies.combatCritters.Logic.visitor.IItemVisitor;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.objects.ItemStack;

/**
 * UserParticipant.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/31/24
 * 
 * @PURPOSE:    participant for a user
 */
public class UserParticipant implements IParticipant {
    private final ICardInventory cardInventory;
    private final IPackInventory packInventory;
    private final ICurrencyInventory currencyInventory;

    public UserParticipant(ICardInventory cardInventory, IPackInventory packInventory, ICurrencyInventory currencyInventory) {
        this.cardInventory = cardInventory;
        this.packInventory = packInventory;
        this.currencyInventory = currencyInventory;
    }

    @Override
    public void add(ItemStack<?> item) {
        IItemVisitor visitor = new TransactionAdd(cardInventory, packInventory,currencyInventory, item.getAmount());
        item.getItem().accept(visitor);
    }

    @Override
    public void remove(ItemStack<?> item) {
        IItemVisitor visitor = new TransactionRemove(cardInventory, packInventory,currencyInventory, item.getAmount());
        item.getItem().accept(visitor);
    }

    @Override
    public boolean has(ItemStack<?> item) {
        TransactionVerify visitor = new TransactionVerify(cardInventory, packInventory,currencyInventory, item.getAmount());
        item.getItem().accept(visitor);
        
        return visitor.isValid();
    }
}
