package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

/**
 * IBoardRow.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-24
 *
 * @PURPOSE:    Interface of Storing the board
 */

public interface IBoardRow {
    /**
     * set the opposing row
     * @param opposing row to set to
     */
    void setOpposing(IBoardRow opposing);

    /**
     * play a card at a position in the row
     *
     * @param pos  position to play at
     * @param card card to play
     * @throws BattleException thrown when the space is occupied
     */
    void playCard(int pos, IBattleCard card) throws BattleException;

    /**
     * get the list of cards (for ui)
     *
     * @return list of cardstates for the cards
     */
    List<CardState> getCardStateList();

    /**
     * get card at a position
     *
     * @param pos position to get from
     * @return card at position (null if no card)
     */
    IBattleCard getCard(int pos);

    /**
     * kill a card at a position in this row
     * @param pos position to kill the card at
     * @throws BattleException thrown if there is no card to kill
     */
    void killCard(int pos) throws BattleException;

    /**
     * transfer a card from this row to another row
     * @param destination destination row to move to
     * @param to destination slot
     * @param from source slot
     */
    void transfer(IBoardRow destination, int to, int from) throws BattleException;

    /**
     * attack the card at the position
     * @param pos position to attack
     * @param damage amount of damage to attack with
     */
    void attack(int pos, int damage);

    /**
     * run the attack phase for this row
     * (have every card in the row attack)
     */
    void runAttackPhase();

    /**
     * get the health object for this row
     * @return health object (null if the row doesnt have a health object)
     */
    IHealth getHealth();

    /**
     * get the damage array for this row
     */
    int[] getDamage();

    /**
     * deal damage from array to this row
     * @param damage damage array to deal from
     */
    void dealDamage(int[] damage);
}
