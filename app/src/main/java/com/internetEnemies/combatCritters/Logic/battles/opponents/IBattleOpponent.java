package com.internetEnemies.combatCritters.Logic.battles.opponents;

import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IBoard;

/**
 * IBattleOpponent.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-27
 *
 * @PURPOSE:    interface for all opponents in battles
 */
public interface IBattleOpponent {
    /**
     * analyze the board and make a move
     * @param board board to use
     */
    void play(IBoard board) throws BattleException;
}
