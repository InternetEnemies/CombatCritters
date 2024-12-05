package com.internetEnemies.combatCritters.Logic.battles.events;

import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;

public interface IBattleEndedHandler {
    /**
     * called when a game is ended
     * @param winner winner of the match or null in the case of a draw
     */
    void gameEnded(IPlayer winner);
}
