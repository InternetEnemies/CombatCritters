package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.objects.Card;

/**
 * IBattleOrchestrator.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/22/24
 *
 * @PURPOSE:    Interface for interacting with a battle
 */
public interface IBattleOrchestrator {
    /**
     * end the players turn
     */
    void endTurn() throws BattleInputException;

    /**
     * play a card at a position
     * @param pos position to play the card
     * @param card Card to play
     */
    void playCard(int pos, Card card) throws BattleInputException;

    /**
     * sacrifice a card for energy
     * @param pos position of the card to sacrifice
     */
    void sacrifice(int pos) throws BattleInputException;
}
