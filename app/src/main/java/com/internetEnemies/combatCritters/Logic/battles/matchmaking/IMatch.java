package com.internetEnemies.combatCritters.Logic.battles.matchmaking;


import java.util.List;

/**
 * IMatch.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/27/24
 *
 * @PURPOSE:    Manage high level battle state (matching -> wager -> battle -> battle complete)
 */
public interface IMatch {
    /**
     * get the list of players in this match
     */
    List<IPlayer> getPlayers();

    /**
     * end the match
     */
    void endMatch(IPlayer winner);
}
