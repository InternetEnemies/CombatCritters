/**
 * DeckBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-February-2024
 *
 * @PURPOSE:     As a deck modifying tool, functions includes addCard and removeCard, etc,
 *               need to initialize with a IDeck,
 *               therefore must be created by the DeckManager.getBuilder()
 */

package com.internetEnemies.combatCritters.Logic;


import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.data.IDeck;
import com.internetEnemies.combatCritters.objects.DeckValidity;

import java.util.List;

public class DeckBuilder{

    private final IDeck deck;

    /**
     * Constructor for DeckBuilder class
     * @param deck the selected deck
     */
    public DeckBuilder(IDeck deck){
        assert deck != null;
        this.deck = deck;
    }

    /**
     * Add a card to the selected deck
     * @param insert the card object to insert with
     */
    public void addCard(Card insert){
        addCard(deck.getTotalCards(),insert);
    }

    /**
     * insert a card into the given slot in the deck
     * @param slot slot to insert at
     * @param insert card to insert
     */
    public void addCard(int slot, Card insert) {
        assert insert != null;
        deck.addCard(slot,insert);
    }

    /**
     * Remove a card from the selected deck
     * @param slot the slot to remove the card from
     */
    public void removeCard(int slot){
        deck.removeCard(slot);
    }

    /**
     * get all cards from the selected deck
     * @return List of Cards in deck
     */
    public List<Card> getCards() {
        return deck.getCards();
    }

    /**
     * Get the number of cards in the deck,
     * a public getter for private function getNumOfCards()
     * @return the number of cards
     */
    public int getTotalNumOfCards(){
        return deck.getTotalCards();
    }

    /**
     * Get the number of a card in the deck
     * @param card the card object wanted to get number from
     * @return the number of a card
     */
    public int getNumOfCard(Card card){
        return deck.countCard(card);
    }

    /**
     * check whether the deck in the builder is valid
     * @return DeckValidity object for the deck
     */
    public DeckValidity validate(){
        return DeckValidator.validateDeck(deck.getCards());
    }
}
