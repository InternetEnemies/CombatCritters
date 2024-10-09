package com.internetEnemies.combatCritters.Logic.inventory.decks;

import com.internetEnemies.combatCritters.objects.User;

/**
 * IDeckManagerFactory.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/9/24
 * 
 * @PURPOSE:    create deck managers
 */
public interface IDeckManagerFactory {
    IDeckManager create(User user);
}
