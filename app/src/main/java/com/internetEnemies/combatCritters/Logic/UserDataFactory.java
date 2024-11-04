package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.Logic.inventory.Bank;
import com.internetEnemies.combatCritters.Logic.inventory.IBank;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.inventory.packs.PackInventoryManager;
import com.internetEnemies.combatCritters.data.*;
import com.internetEnemies.combatCritters.objects.User;

/**
 * UserDataFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/6/24
 * 
 * @PURPOSE:    provide hsqldb data layer
 */
public class UserDataFactory implements IUserDataFactory{
    private final Database database;
    public UserDataFactory(Database database) {
        this.database = database;
    }
    @Override
    public IDeckInventory getDeckInventory(User user) {
        return database.getDeckInventory(user);
    }

    @Override
    public ICardInventory getCardInventory(User user) {
        return database.getCardInventory(user);
    }

    @Override
    public IPackInventory getPackInventory(User user) {
        return database.getPackInventory(user);
    }

    @Override
    public ICurrencyInventory getCurrencyInventory(User user) {
        return database.getCurrencyInventory(user);
    }

    @Override
    public IProfilesDB getProfilesDB(User user) {
        return database.getProfilesDB(user);
    }

    @Override
    public IFriendsDB getFriendsDB(User user) {
        return database.getFriendsDB(user);
    }

    @Override
    public IPackInventoryManager getPackInventoryManger(User user) {
        return new PackInventoryManager(getPackInventory(user), getCardInventory(user));
    }

    @Override
    public IBank getBank(User user) {
        return new Bank(database.getCurrencyInventory(user));
    }
}
