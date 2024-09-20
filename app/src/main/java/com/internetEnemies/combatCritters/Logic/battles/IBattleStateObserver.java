package com.internetEnemies.combatCritters.Logic.battles;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.List;

/**
 * IBattleStateObserver.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/21/24
 *
 * @PURPOSE:    provide interface for a large observer of BattleState changes
 */
public interface IBattleStateObserver {
    /**
     * set whether it is the players turn
     * @param isPlayerTurn is it the players turn?
     */
    void setPlayerTurn(boolean isPlayerTurn);

    /**
     * set players health
     */
    void setPlayerHealth(int health);

    /**
     * set health of the enemy
     */
    void setEnemyHealth(int health);

    /**
     * set energy
     */
    void setEnergy(int energy);

    /**
     * set the cards in the player's hand
     */
    void setHand(List<Card> cards);

    /**
     * set the size of the players draw pile
     */
    void setDrawPileSize(int size);

    /**
     * set the buffer cards
     * @param cardStates list of card states
     */
    void setBufferCards(List<CardState> cardStates);
    /**
     * set the enemy cards
     * @param cardStates list of card states
     */
    void setEnemyCards(List<CardState> cardStates);
    /**
     * set the player cards
     * @param cardStates list of card states
     */
    void setPlayerCards(List<CardState> cardStates);
}
