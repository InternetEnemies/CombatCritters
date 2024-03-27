package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.IBattleCard;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

public interface IBoardRow {
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
     * attack the card at the position
     * @param pos position to attack
     * @param damage amount of damage to attack with
     */
    void attack(int pos, int damage);
}
