package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

/**
 * IBoard.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    interface of storing the board object
 */

public interface IBoard {

    /**
     * @return the buffer row
     */
    IBoardRow getBuffer();

    /**
     * @return the enemy row
     */
    IBoardRow getEnemy();

    /**
     * @return the player row
     */
    IBoardRow getPlayer();

    /**
     * advance the cards in the buffer into enemy. (cards in buffer move into free spaces in enemy)
     */
    void advanceBuffer();
}
