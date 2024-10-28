package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.Logic.inventory.IBank;
import com.internetEnemies.combatCritters.Logic.inventory.packs.IPackInventoryManager;
import com.internetEnemies.combatCritters.data.*;
import com.internetEnemies.combatCritters.objects.User;

/**
 * IUserDataFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/6/24
 * 
 * @PURPOSE:    interface for getting data layer objects
 */
public interface IUserDataFactory {
    /**
     * get DeckInventory for a user
     * @param user user for the deck inventory
     */
    IDeckInventory getDeckInventory(User user);

    /**
     * get card inventory for the user
     * @param user user to get the card inventory
     */
    ICardInventory getCardInventory(User user);

    /**
     * get the pack inventory for the user
     * @param user user to get the inventor for
     */
    IPackInventory getPackInventory(User user);

    /**
     * get profiles db for a user 
     * @param user user to get the db for
     */
    IProfilesDB getProfilesDB(User user);

    /**
     * get a friend db for a user
     * @param user user to get the db for
     */
    IFriendsDB getFriendsDB(User user);

    /**
     * get pack inventory manager for a user
     * @param user user to get the pack manager for
     */
    IPackInventoryManager getPackInventoryManger(User user);

    /**
     * get the bank for a user
     * @param user user to get the bank for
     * @return bank for the user
     */
    IBank getBank(User user);
}
