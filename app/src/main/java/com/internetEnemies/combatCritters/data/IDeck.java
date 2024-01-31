package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;
import java.util.Map;

/**
 * Interface to the deck database
 */
public interface IDeck {
    /**
     * get the list of cards in this deck in order (can have duplicate cards)
     * @return List of cards
     */
    List<Card> getCards();

    /**
     * get the card from the given slot
     * @param slot slot to get card from
     * @return Card in slot
     */
    Card getCard(int slot);

    /**
     * adds a new card to the Deck
     * @param card Card to add
     */
    void addCard(Card card);

    /**
     *
     * @param slot
     */
    void removeCard(int slot);

    /**
     * get the amount of a card in the deck
     * @param card card to lookup
     * @return number of times the card appears in the deck
     */
    int countCard(Card card);

    /**
     * get the counts of all the cards in the deck
     * @return map of card->amount
     */
    Map<Card,Integer> countCards();
}
