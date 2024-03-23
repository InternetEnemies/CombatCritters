package com.internetEnemies.combatCritters.Logic.battles.registry;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * BattleRegistry.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE: provide battles from the set of battles
 */
public class BattleRegistry {
    /**
     * get a battle from its id and start it with a deck
     * @param id id of the battle to start
     * @param deck list of cards to start with
     * @return Battle with the id created with the deck
     */
    public Battle getBattle(int id, List<Card> deck) {
        //todo actually implement this
        return new Battle(deck);
    }
}
