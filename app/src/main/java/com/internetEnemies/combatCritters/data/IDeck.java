package com.internetEnemies.combatCritters.data;

import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.DeckDetails;
import com.internetEnemies.combatCritters.objects.ItemStack;

import java.util.List;

/**
 * Interface to the deck database
 */
public interface IDeck{

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
    void addCard(int slot,Card card);

    /**
     * removes the card in the slot
     * @param slot slot of the card to remove
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
     * @return list of item stacks of card
     */
    List<ItemStack<Card>> countCards();

    /**
     * get the Deck object this db instance is related to
     * @return Deck object for this deck
     */
    DeckDetails getInfo();

    /**
     * get an unmodifiable list of cards
     * @return list of Card
     */
    List<Card> getCards();

    /**
     * get the total number of cards in the deck
     * @return number of cards in the deck
     */
    int getTotalCards();
}
