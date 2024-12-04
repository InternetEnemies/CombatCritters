package com.internetEnemies.combatCritters.Logic.battles.player;

import com.internetEnemies.combatCritters.Logic.battles.matchmaking.IPlayer;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.*;

/**
 * IBattlePlayer.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    interface for a player in a battle
 */
public interface IBattlePlayer {
    /**
     * get the damage array created
     */
    int[] getDamage();

    /**
     * deal damage from damage array
     * @param damage damage array to use
     */
    void dealDamage(int[] damage);

    /**
     * advance buffer cards into play
     */
    void advanceBuffer();

    /**
     * set the player turn to active
     */
    void startTurn();

    /**
     * get the player in play cards
     */
    IBoardRow getPlay();

    /**
     * get the players buffer cards
     */
    IBoardRow getBuffer();

    /**
     * get the players health
     */
    IHealth getHealth();

    /**
     * get the players energy
     */
    IEnergy getEnergy();

    /**
     * get the players turn state
     */
    ITurn getTurn();

    /**
     * get the players hand state
     */
    IHand getHand();

    /**
     * get the player object for this BattlePlayer
     */
    IPlayer getPlayer();
}
