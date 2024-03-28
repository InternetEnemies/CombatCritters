package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.IEventSystem;

/**
 * IBoardFactory.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/28/24
 *
 * @PURPOSE:    interface for getting boards
 */
public interface IBoardFactory {
    /**
     * get a new board
     * @param eventSystem event system for the board
     * @param size size of the board
     * @param buffer buffer row
     * @param enemy enemy row
     * @param player player row
     * @return new board
     */
    IBoard getBoard(IEventSystem eventSystem, int size, IBoardRow buffer, IBoardRow enemy, IBoardRow player);
}
