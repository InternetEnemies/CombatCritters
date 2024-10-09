package com.internetEnemies.combatCritters.Logic;

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
    public IProfilesDB getProfilesDB(User user) {
        return database.getProfilesDB(user);
    }

    @Override
    public IFriendsDB getFriendsDB(User user) {
        return database.getFriendsDB(user);
    }
}
