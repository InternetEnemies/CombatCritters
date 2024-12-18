package com.internetEnemies.combatCritters.Logic.battles.matchmaking;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.IBattleStateObserver;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.User;

import java.util.List;

/**
 * IPlayer.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/27/24
 *
 * @PURPOSE: provide a unified interface to battles (from matching to game end) for a player
 */
public interface IPlayer {
    /**
     * get the players battle orchestrator instance (nullable)
     */
    IBattleOrchestrator getOrchestrator();

    /**
     * set the battle orchestrator for this player
     */
    void setOrchestrator(IBattleOrchestrator orchestrator);

    /**
     * get the players battle state observer
     */
    IBattleStateObserver getStateObserver();

    /**
     * get the user for the player
     */
    User getUser();

    /**
     * get the match state observer for this player
     */
    IMatchStateObserver getMatchStateObserver();

    /**
     * set the players active deck
     */
    void setDeck(List<Card> deck);

    /**
     * get the players active deck
     */
    List<Card> getDeck();
}
