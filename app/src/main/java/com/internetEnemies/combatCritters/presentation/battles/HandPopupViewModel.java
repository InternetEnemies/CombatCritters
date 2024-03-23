package com.internetEnemies.combatCritters.presentation.battles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.internetEnemies.combatCritters.objects.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * HandPopupViewModel.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    state holder for the hand popup
 */
public class HandPopupViewModel extends ViewModel {
    private final MutableLiveData<List<Card>> cards;
    private final MutableLiveData<Card> selected;

    public HandPopupViewModel(){
        this.cards = new MutableLiveData<>(new ArrayList<>());
        this.selected = new MutableLiveData<>();
    }

    /**
     * get the cards live data for the hand
     */
    public LiveData<List<Card>> getCards() {
        return cards;
    }

    /**
     * set the cards in the hand
     */
    public void setCards(List<Card> cards){
        assert cards != null;
        this.cards.setValue(cards);
    }

    /**
     * get the livedata for the currently selected card
     */
    public LiveData<Card> getSelected() {
        return selected;
    }

    /**
     * set the currently selected card
     */
    public void setSelected(Card card) {
        this.selected.setValue(card);
    }

    /**
     * clear the selection
     */
    public void clearSelected() {
        this.selected.setValue(null);
    }
}