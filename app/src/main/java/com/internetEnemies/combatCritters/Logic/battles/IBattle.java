package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IEnergy;
import com.internetEnemies.combatCritters.Logic.battles.stateHandlers.IHealth;

public interface IBattle {
    /**
     * play a card at any position on the board
     * @param pos position within row
     * @param row row to play in
     * @param card card to play
     * @param force if true the any card in the desired play position will be killed before playing
     * @return true iff the card was played at the position
     */
    boolean playCardRaw(int pos, int row, BattleCard card, boolean force);

    /**
     * damage a position on the board
     * @param pos position within row
     * @param row row to act on
     */
    void damagePosition(int pos, int row);

    /**
     * heal a position on the board
     * @param pos position in row
     * @param row row to act on
     */
    void healPosition(int pos, int row);

    /**
     * get the energy handler for the battle
     * @return Energy object instance for the battle
     */
    IEnergy getEnergy();

    /**
     * get the players health object
     * @return Health object for the player
     */
    IHealth getPlayerHealth();

    /**
     * get the enemies health object
     * @return health object for the enemy
     */
    IHealth getEnemyHealth();
}
