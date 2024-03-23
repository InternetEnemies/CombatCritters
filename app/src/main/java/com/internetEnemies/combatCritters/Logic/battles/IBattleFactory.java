package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * IBattleFactory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    interface for battle factories which provide BattleOrchestrators to use
 */
public interface IBattleFactory {
    /**
     * Factory method for Battles / BattleOrchestrators
     * @param uiProvider observer of the battle, will be sent events as the battle is played
     * @param battleId id of the battle to start
     * @param deck deck the player wants to use
     * @return BattleOrchestrator for a battle
     */
    BattleOrchestrator getBattle(IBattleStateObserver uiProvider, int battleId, List<Card> deck);
}
