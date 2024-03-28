package com.internetEnemies.combatCritters.Logic.battles.registry;

import com.internetEnemies.combatCritters.Logic.battles.Battle;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.Logic.battles.events.IVoidEventListener;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * IBattleRegistry.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    interface for registries of battles
 */
public interface IBattleRegistry {
    /**
     * get a battle from its id and start it with a deck
     *
     * @param id   id of the battle to start
     * @param deck list of cards to start with
     * @return Battle with the id created with the deck
     */
    Battle getBattle(IBattleStateObserver uiProvider, int id, List<Card> deck, IVoidEventListener onWin, IVoidEventListener onLoss);
}
