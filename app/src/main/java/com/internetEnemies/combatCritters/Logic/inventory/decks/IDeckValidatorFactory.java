package com.internetEnemies.combatCritters.Logic.inventory.decks;

import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.objects.User;

/**
 * IDeckValidatorFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/6/24
 * 
 * @PURPOSE:    provide deck validators
 */
public interface IDeckValidatorFactory {
    /**
     * provide a deck validator for a user
     * @param cardInventory inventory for the user
     */
    IDeckValidator createValidator(ICardInventory cardInventory);
}
