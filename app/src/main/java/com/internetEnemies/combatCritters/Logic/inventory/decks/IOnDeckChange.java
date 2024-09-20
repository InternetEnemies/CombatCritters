package com.internetEnemies.combatCritters.Logic.inventory.decks;

import com.internetEnemies.combatCritters.objects.DeckValidity;

/**
 * IOnDeckChange.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-04
 *
 * @PURPOSE:    DeckBuilder change listener
 */
public interface IOnDeckChange {
    void onChange(DeckValidity validity);
}
