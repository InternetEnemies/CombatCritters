package com.internetEnemies.combatCritters.Logic;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.IDeckInventory;
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
}
