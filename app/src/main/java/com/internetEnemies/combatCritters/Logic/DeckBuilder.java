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

public class DeckBuilder implements IDeckBuilder {

    private final IDeck deck;

    /**
     * Constructor for DeckBuilder class
     * @param deck the selected deck
     */
    public DeckBuilder(IDeck deck){
        assert deck != null;
        this.deck = deck;
    }

    @Override
    public void addCard(Card insert){
        addCard(deck.getTotalCards(),insert);
    }

    @Override
    public void addCard(int slot, Card insert) {
        assert insert != null;
        deck.addCard(slot,insert);
    }

    @Override
    public void removeCard(int slot){
        deck.removeCard(slot);
    }

    @Override
    public List<Card> getCards() {
        return deck.getCards();
    }

    @Override
    public int getTotalNumOfCards(){
        return deck.getTotalCards();
    }

    @Override
    public int getNumOfCard(Card card){
        return deck.countCard(card);
    }

    @Override
    public DeckValidity validate(){
        return DeckValidator.validateDeck(deck.getCards());
    }
}
