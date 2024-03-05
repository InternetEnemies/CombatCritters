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

import java.util.ArrayList;
import java.util.List;

public class DeckBuilder implements IDeckBuilder {

    private final IDeck deck;
    private final IDeckValidator validator;
    private final List<IOnDeckChange> onChangeListeners;

    /**
     * Constructor for DeckBuilder class
     * @param deck the selected deck
     */
    public DeckBuilder(IDeck deck){
        this(deck, new DeckValidator());
    }
    public DeckBuilder(IDeck deck, IDeckValidator validator) {
        assert deck != null;
        this.deck = deck;

        this.onChangeListeners = new ArrayList<>();
        this.validator = validator;
    }

    @Override
    public void addCard(Card insert){
        addCard(deck.getTotalCards(),insert);
    }

    @Override
    public void addCard(int slot, Card insert) {
        assert insert != null;
        deck.addCard(slot,insert);

        onChange();
    }

    @Override
    public void removeCard(int slot){
        deck.removeCard(slot);

        onChange();
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
        return this.validator.validate(deck);
    }

    @Override
    public void observe(IOnDeckChange onDeckChange) {
        this.onChangeListeners.add(onDeckChange);
    }

    /**
     * fire OnDeckChange observers
     */
    private void onChange() {
        DeckValidity validity = validate();
        for(IOnDeckChange onChange : this.onChangeListeners) {
            onChange.onChange(validity);
        }
    }
}

