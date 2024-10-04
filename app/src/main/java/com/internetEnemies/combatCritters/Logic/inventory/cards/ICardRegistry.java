package com.internetEnemies.combatCritters.Logic.inventory.cards;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * ICardRegistry.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/4/24
 * 
 * @PURPOSE:    interface for the card registry
 */
public interface ICardRegistry {
    /**
     * get a card from its id
     * @param id id of the cards to get
     * @return card with the given id or null if no such card exists
     */
    Card getCard(int id);

    /**
     * get a list of cards from their ids
     * @param ids list of ids to get
     * @return list of cards with the ids
     */
    List<Card> getCards(List<Integer> ids);

    /**
     * get all cards in the game
     */
    List<Card> getAllCards();
}
