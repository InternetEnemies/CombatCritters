package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.cards.BattleCard;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleException;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

public interface IBoard {
    /**
     * play a card into the buffer at a position
     *
     * @param pos  position to play
     * @param card card to play
     * @throws BattleException thrown when trying to play a card in an occupied space
     */
    void playCardBuffer(int pos, BattleCard card) throws BattleException;

    /**
     * play a card into the enemy cards at a position
     *
     * @param pos  position to play
     * @param card card to play
     * @throws BattleException thrown when trying to play a card in an occupied space
     */
    void playCardEnemy(int pos, BattleCard card) throws BattleException;

    /**
     * play a card into the player cards at a position
     *
     * @param pos  position to play
     * @param card card to play
     * @throws BattleException thrown when trying to play a card in an occupied space
     */
    void playCardPlayer(int pos, BattleCard card) throws BattleException;

    /**
     * get a card from the board
     *
     * @param pos position in row
     * @param row row to get from
     * @return card from the given position in the row
     */
    BattleCard getCard(int pos, int row);

    /**
     * @return list of cards in the buffer row
     */
    List<CardState> getBuffer();

    /**
     * @return list of cards in the enemy row
     */
    List<CardState> getEnemy();

    /**
     * @return list of cards in the player row
     */
    List<CardState> getPlayer();
}
