package com.internetEnemies.combatCritters.Logic.battles.stateHandlers;

import com.internetEnemies.combatCritters.Logic.battles.events.EventHost;
import com.internetEnemies.combatCritters.Logic.battles.events.HandEvent;
import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;

/**
 * IHand.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     12/4/24
 *
 * @PURPOSE:    interface for managing hand state
 */
public interface IHand {
    /**
     * pull multiple cards into the hand
     * @param amount amount of cards to pull
     */
    void pullCards(int amount);

    /**
     * pull a card into the hand
     */
    void pullCard();
    List<Card> getCards();
    EventHost<HandEvent> getEventHost();

    /**
     * remove a card from hand
     * @param card card to remove
     */
    void remove(Card card);
}
