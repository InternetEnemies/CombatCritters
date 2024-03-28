package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

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
