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

import java.util.List;
import java.util.Map;

public class DeckBuilder{

    private final IDeck deck;

    /**
     * Constructor for DeckBuilder class
     * @param deck the selected deck
     */
    public DeckBuilder(IDeck deck){
        this.deck = deck;
    }

    /**
     * Add a card to the selected deck
     * @param insert the card object to insert with
     */
    public void addCard(Card insert){
        addCard(0,insert);
    }

    /**
     * insert a card into the given slot in the deck
     * @param slot slot to insert at
     * @param insert card to insert
     */
    public void addCard(int slot, Card insert) {
        if (insert == null) {
            throw new NullPointerException();
        }
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
     * Get the index of the card in a deck, depends on getNumOfCards()
     * @param id the card wanted to find
     * @return the index of the card or '-1' stand for not found the card
     */
    private int getCardIndex(int id){
        int totalNum = getTotalNumOfCards();
        if (totalNum == 0){ return -1; } //this should raise exception and abort the calling method
        for(int i=0;i<totalNum;i++) {
            if (deck.getCard(i).getId() == id) { return i; }
        }
        return -1; //this should raise exception and abort the mother method
    }
}
