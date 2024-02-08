package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.List;
import java.util.Map;

/**
 * This interface handles interactions with database of Cards.
 * This interface doesn't care about order, just amounts and types of cards.
 */
public interface ICardInventory{
    /**
     * get the number of a given card in the inventory
     * @param card type of card to lookup
     * @return number of cards of that type
     */
    int getCardAmount(Card card);

    /**
     * Add a new card to the inventory
     * @param card card to add
     */
    void addCard(Card card);

    /**
     * add multiple cards to the inventory
     * @param cards list of cards to add
     */
    void addCards(List<Card> cards);

    /**
     * removes [amount] cards from the inventory
     * @param card type of card to remove
     * @param amount number of that card to remove
     */
    void removeCard(Card card, int amount);

    /**
     * removes a (one) card from the inventory
     * @param card type of card to remove
     */
    void removeCard(Card card);

    /**
     * get an unmodifiable map of cards and their quantities
     * @return a map of cards and their amounts
     */
    Map<Card,Integer> getCards();
}
