/**
 * IDeckBuilder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-11
 *
 * @PURPOSE:    Builder for deck
 */

package com.internetEnemies.combatCritters.Logic.inventory.decks;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.util.List;

public interface IDeckBuilder {
    /**
     * Add a card to the selected deck
     *
     * @param insert the card object to insert with
     */
    void addCard(Card insert);

    /**
     * insert a card into the given slot in the deck
     *
     * @param slot   slot to insert at
     * @param insert card to insert
     */
    void addCard(int slot, Card insert);

    /**
     * Remove a card from the selected deck
     *
     * @param slot the slot to remove the card from
     */
    void removeCard(int slot);

    /**
     * get all cards from the selected deck
     *
     * @return List of Cards in deck
     */
    List<Card> getCards();

    /**
     * Get the number of cards in the deck,
     * a public getter for private function getNumOfCards()
     *
     * @return the number of cards
     */
    int getTotalNumOfCards();

    /**
     * Get the number of a card in the deck
     *
     * @param card the card object wanted to get number from
     * @return the number of a card
     */
    int getNumOfCard(Card card);

    /**
     * check whether the deck in the builder is valid
     *
     * @return DeckValidity object for the deck
     */
    DeckValidity validate();

    /**
     * observe when the deck changes state
     * @param onDeckChange onChange listener
     */
    void observe(IOnDeckChange onDeckChange);
}
